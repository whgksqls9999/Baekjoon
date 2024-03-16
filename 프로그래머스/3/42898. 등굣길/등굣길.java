class Solution {

    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int INF = 1_000_000_007;
        
        int[][] map = new int[m+1][n+1];
        for (int[] pos : puddles){
            if(pos.length == 0) continue;
            map[pos[0]][pos[1]] = 1;
        }
        
        for (int i = 0; i <= m; i++){
            map[i][0] = 1;
        }
        for (int i = 0; i <= n; i++){
            map[0][i] = 1;
        }
        
        int[][] dp = new int[m+1][n+1];
        dp[1][1] = 1;
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if(map[i][j] == 1) continue;
                int left = map[i][j-1] != 1 ? dp[i][j-1] : 0;
                int top = map[i-1][j] != 1 ? dp[i-1][j] : 0;
                
                dp[i][j] = (left + top + dp[i][j]) % INF;
            }
        }
        
        answer = dp[m][n];
        return answer;
    }
}