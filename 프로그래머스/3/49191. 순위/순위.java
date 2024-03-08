import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        List<Integer>[] nodes = new ArrayList[n+1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }
        
        HashSet<Integer>[] win = new HashSet[n+1];
        HashSet<Integer>[] lose = new HashSet[n+1];
        
        for (int i = 1; i < lose.length; i++){
            lose[i] = new HashSet<>();
            win[i] = new HashSet<>();
        }
        
        int[] indegree = new int[n+1];
        for (int[] res : results){
            nodes[res[0]].add(res[1]);
        
            win[res[0]].add(res[1]);
            lose[res[1]].add(res[0]);
            
            indegree[res[1]]++;
        }
        
        BFS(nodes, indegree, lose);
        
        nodes = new ArrayList[n+1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }
        
        indegree = new int[n+1];
        for (int[] res : results){
            nodes[res[1]].add(res[0]);
            
            indegree[res[0]]++;
        }
        
        BFS(nodes, indegree, win);
        
        for (int i = 1; i < indegree.length; i++){
            if (win[i].size() + lose[i].size() == n-1){
                answer++;
            }
        }
        return answer;
    }
    
    public void BFS(List<Integer>[] nodes, int[] indegree, HashSet<Integer>[] set){
        Queue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 1; i < indegree.length; i++){
            if (indegree[i] == 0){
                pq.add(i);
            }
        }
        
        int cnt = 0;
        
        while(!pq.isEmpty()){
            int cur = pq.poll();
            
            for (int i : nodes[cur]){
                set[i].add(cur);
                for (int j : set[cur]){
                    set[i].add(j);
                }
                
                indegree[i]--;
                
                if(indegree[i] == 0){
                    pq.add(i);
                }
            }
        }
    }
}