import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int n, int[][] vertex) {
        int answer = 0;
        
        List<Integer>[] nodes = new ArrayList[n+1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for (int[] edge : vertex){
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }
        
        answer = BFS(nodes, n);
        
        return answer;
    }
    
    public int BFS(List<Integer>[] nodes, int n) {
        int[] visited = new int[n+1];
        for(int i = 2; i < visited.length; i++){
            visited[i] = -1;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        
        int maxDist = 0;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for (int next : nodes[cur]){
                if(visited[next] == -1){
                    visited[next] = visited[cur] + 1;
                    queue.add(next);
                    maxDist = Math.max(maxDist, visited[next]);
                }
            }
        }
                
        int result = 0;
        
        for (int i = 1; i < visited.length; i++){
            if (visited[i] == maxDist){
                result++;
            }
        }
        
        return result;
    }
}