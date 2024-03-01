import java.util.Arrays;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        int lo = 0, hi = distance;
        
        while(lo <= hi){
            int mid = (hi + lo) / 2;
            
            int pre = 0;
            int cnt = 0;
            
            for (int i = 0; i < rocks.length; i++){    
                if (rocks[i] - pre < mid){
                    cnt++;
                } else {
                    pre = rocks[i];
                }
            } 
            
            if (distance - pre < mid){
                cnt++;
            }
            
            if (cnt <= n){
                answer = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return answer;
    }
}