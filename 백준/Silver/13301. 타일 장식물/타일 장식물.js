const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const dp = Array(N+1).fill(0);

dp[0] = BigInt(0);
dp[1] = BigInt(1);
dp[2] = BigInt(1);


(function solution(){
    for (let i = 3; i <= N; ++i){
        dp[i] = dp[i-1] + dp[i-2];
    }
    console.log((dp[N] * BigInt(2) + (dp[N] + dp[N-1])*BigInt(2)).toString());
}());