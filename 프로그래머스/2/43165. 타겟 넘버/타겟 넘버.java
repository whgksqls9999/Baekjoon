import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        int[] ops = new int[numbers.length];
        
        DFS(0, numbers.length, numbers, ops, target);
        
        return answer;
    }
    
    public void DFS(int cur, int limit, int[] numbers, int[] ops, int target){
         if (cur == limit){
             int result = 0;
             
             for (int i = 0; i < ops.length; i++){
                 if (ops[i] == 0){
                     result -= numbers[i];
                 } else {
                     result += numbers[i];
                 }
             }
             
             if (result == target){
                 answer++;
             }
             return;
         }
        
        for (int i = 0; i < 2; i++){
            int prev = ops[cur];
            ops[cur] = i;
            DFS(cur + 1, limit, numbers, ops, target);
            ops[cur] = prev;
        }
    }
}