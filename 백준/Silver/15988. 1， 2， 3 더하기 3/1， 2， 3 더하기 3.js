const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
const T = Number(input[0]);

(function solution(){
    let answer = [];

    const dp = Array(1000001).fill(0);
    dp[1] = 1;
    dp[2] = dp[1] + 1;
    dp[3] = dp[1] + dp[2] + 1;
    let pre = 4;
    for (let tc = 1; tc <= T; ++tc){
        let cur = Number(input[tc]);
        if(dp[cur] === 0){
            for (let i = pre; i <= cur; ++i){
                dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1_000_000_009;
            }
        }
        pre = cur;
        answer.push(dp[cur]);
    }
    
    console.log(answer.join('\n'));
}());