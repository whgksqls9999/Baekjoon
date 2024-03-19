import java.util.*;

class Solution {
    public int[] solution(int n) {
             
        int[][] arr = new int[n][n];
        
        int[] dr = new int[]{1, 0, -1};
        int[] dc = new int[]{0, 1, -1};
        
        int limit = 0;
        for (int i = 1; i <= n; i++){
            limit += i;
        }

        int dir = 0;
        int cur = 0;
        int r = 0, c = 0;
        int nr = r, nc = c;

        while (cur < limit){
            arr[nr][nc] = ++cur;    
            
            int checkR = nr + dr[dir];
            int checkC = nc + dc[dir];
            if (checkR >= 0 && checkR < n && checkC >= 0 && checkC < n && arr[checkR][checkC] == 0) {
                nr = checkR;
                nc = checkC;
                continue;
            }

            dir = (dir + 1) % 3;
            nr += dr[dir];
            nc += dc[dir];
        }

        
        int[] answer = new int[limit];
        
        int idx = 0;
        for (int[] i : arr){
            for (int j : i){
                if (j != 0){
                     answer[idx++] = j;
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}