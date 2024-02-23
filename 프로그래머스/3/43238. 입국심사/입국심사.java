class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long lo = 1, hi = (long) n * 1_000_000_000;
        while(lo < hi){
            long mid = lo + (hi - lo) / 2;

            long curTime = 0;
            for (int i : times){
                curTime += mid / i;
            }
            
            if (curTime < n){
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        
        answer = hi;
        
        return answer;
    }
}