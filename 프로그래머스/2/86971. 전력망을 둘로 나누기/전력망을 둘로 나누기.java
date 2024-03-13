import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        List<Integer>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires){
            nodes[wire[0]].add(wire[1]);
            nodes[wire[1]].add(wire[0]);
        }
        
        for (int[] wire : wires){
            for (int i = 0; i < nodes[wire[0]].size(); i++){
                if (nodes[wire[0]].get(i) == wire[1]){
                    nodes[wire[0]].remove(i);
                    break;
                }
            }
            
            for (int i = 0; i < nodes[wire[1]].size(); i++){
                if (nodes[wire[1]].get(i) == wire[0]){
                    nodes[wire[1]].remove(i);
                    break;
                }
            }
            
            boolean[] visited = new boolean[n + 1];
            
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++){
                if(visited[i]) continue;
                
                list.add(BFS(i, visited, nodes));
            }
            
            nodes[wire[0]].add(wire[1]);
            nodes[wire[1]].add(wire[0]);
            
            if (list.size() != 2) continue;
            
            answer = Math.min(answer, Math.abs(list.get(1) - list.get(0)));
        }
        
        return answer;
    }
    
    public int BFS(int num, boolean[] visited, List<Integer>[] nodes){
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[num] = true;
        queue.add(num);
        
        int cnt = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++;
            
            for (int i : nodes[cur]){
                if (visited[i]) continue;
                visited[i] = true;
                queue.add(i);
            }
        }
        return cnt;
    }
}