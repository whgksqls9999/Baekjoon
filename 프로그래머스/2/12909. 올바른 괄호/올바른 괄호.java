import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    boolean solution(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        
        for (int j = 0; j < s.length(); j++){
            char i = s.charAt(j);
            
            if (!stack.isEmpty()){
                if (i == ')' && stack.peek() == '('){
                    stack.pop();
                } else {
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        
        return stack.isEmpty();
    }
}