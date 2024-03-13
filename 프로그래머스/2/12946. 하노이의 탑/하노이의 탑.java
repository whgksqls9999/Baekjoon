import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    int[][] answer;
    public int[][] solution(int n) {
        move(1, 2, 3, n);
        
        answer = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    
    public void move(int from, int mid, int to, int cnt){
        if (cnt == 0) {
            return;
        }
        
        
        move(from, to, mid, cnt - 1);
        list.add(new int[]{from, to});
        move(mid, from, to, cnt - 1);
    }
}