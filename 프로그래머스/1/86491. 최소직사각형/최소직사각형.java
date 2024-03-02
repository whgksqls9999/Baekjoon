class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int max = 0;
        int min = 0;
        
        for (int i = 0; i < sizes.length; i++){
            int maxLen = Math.max(sizes[i][0], sizes[i][1]);
            int minLen = Math.min(sizes[i][0], sizes[i][1]);
            
            max = Math.max(max, maxLen);
            min = Math.max(min, minLen);
        }
        
        return max * min;
    }
}