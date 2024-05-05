import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 100_000 * 200;
        
        ArrayList<int[]>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();            
        }
        
        for (int[] fare : fares){
            int start = fare[0];
            int end = fare[1];
            int fee = fare[2];
            
            nodes[start].add(new int[]{end, fee});
            nodes[end].add(new int[]{start, fee});
        }
        
        int[] fromS = dijkstra(nodes, s, n);
        int[] fromA = dijkstra(nodes, a, n);
        int[] fromB = dijkstra(nodes, b, n);
        
        for (int i = 1; i < fromS.length; i++){
            answer = Math.min(answer, fromS[i] + fromA[i] + fromB[i]);
        }
        
        
        return answer;
    }
    
    public int[] dijkstra(ArrayList<int[]>[] nodes, int s, int n){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        pq.add(new int[]{0, s});
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100_000 * 200);
        dist[s] = 0;
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            for (int[] next : nodes[cur[1]]){  
                int nextNode = next[0];
                int fee = next[1];
                
                if (dist[nextNode] > dist[cur[1]] + fee){
                    dist[nextNode] = dist[cur[1]] + fee;
                    pq.add(new int[]{dist[nextNode], nextNode});
                }
            }
        }
        
        return dist;
    }
}