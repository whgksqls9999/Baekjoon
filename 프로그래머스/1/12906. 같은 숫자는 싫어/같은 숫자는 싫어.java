import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {
    public int[] solution(int []arr) {
        
        
        Deque<Integer> deque = new ArrayDeque<>();
    
        for (int i : arr){
            if (deque.isEmpty() || deque.peekLast() != i){
                deque.addLast(i);
            }
        }
    
        int[] answer = new int[deque.size()];
        int idx = 0;
        
        while(!deque.isEmpty()){
            answer[idx++] = deque.pollFirst();
        }
        return answer;
    }
}