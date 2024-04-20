function solution(n) {
    let answer = 0;
    
    let INF = 1234567;
    const dp = Array(n+1).fill(0);
    dp[1] = 1;
    dp[2] = 2;
    
    for (let i = 3; i < dp.length; i++){
        dp[i] = (dp[i-1] + dp[i-2]) % INF;
    }
    
    answer = dp[dp.length-1] % INF;


    return n === 1 ? 1 : answer;
}