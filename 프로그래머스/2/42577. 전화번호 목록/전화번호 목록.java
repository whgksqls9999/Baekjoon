import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book, (a, b) -> a.length() - b.length());
        Set<String> set = new HashSet<>();
        
        outer: for (String phoneNumber : phone_book){
            for (int i = 0; i < phoneNumber.length(); ++i){
                if(set.contains(phoneNumber.substring(0,i))){
                    answer = false;
                    break outer;
                }
                
                set.add(phoneNumber);
            }
        }

        
        return answer;
    }
}