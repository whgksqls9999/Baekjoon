import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        int answer = 0;
        
        // 방문하지 않은 곳은 BFS로 들어가며 answer 1 증가
        for (int i = 0; i < n; ++i){
            if(!visited[i]){
                ++answer;
                BFS(i, computers);
            }
        }
        return answer;
    }
    
    public void BFS(int num, int[][] computers){
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[num] = true;
        queue.offer(num);
        
        while(!queue.isEmpty()){
            int now = queue.poll();
            
            for (int i = 0; i < computers.length; ++i){
                if (computers[now][i] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}