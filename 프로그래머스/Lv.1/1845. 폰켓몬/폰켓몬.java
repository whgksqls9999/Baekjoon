import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        int cnt = nums.length / 2;
        Set<Integer> set = new HashSet<>();
        
        for(int i : nums){
            set.add(i);
        }
        
        answer = cnt > set.size() ? set.size() : cnt;
        
        return answer;
    }
}