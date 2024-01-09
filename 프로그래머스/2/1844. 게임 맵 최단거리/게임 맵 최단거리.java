import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int solution(int[][] maps) {        
        int answer = BFS(maps, 0, 0);
        
        return answer;
    }
    
    public int BFS(int[][] maps, int r, int c){
        Queue<int[]> queue = new ArrayDeque<>();
        
        int[] dr = new int[]{-1,0,1,0};
        int[] dc = new int[]{0,1,0,-1};
        
        int N = maps.length;
        int M = maps[0].length;
        
        int[][] visited = new int[N][M];
        visited[r][c] = 1;
        
        queue.offer(new int[]{r,c});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == N-1 && cur[1] == M-1){
                return visited[cur[0]][cur[1]];
            }
            
            for (int i = 0; i < 4; ++i){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M){
                    if(visited[nr][nc] == 0 && maps[nr][nc] == 1){
                        visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }    
        }
        
        return -1;
    }
}