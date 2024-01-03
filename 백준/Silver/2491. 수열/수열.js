const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
const N = Number(input[0]);
const arr = input[1].split(' ').map(Number);
const dp = Array(2).fill(0).map(() => Array(N).fill(0));
dp[0][0] = 1;
dp[1][0] = 1;

(function solution(){
    let answer = 1;
    for (let i = 1; i < N; ++i){
        if(arr[i] >= arr[i-1]){
            dp[0][i] = dp[0][i-1]+1;
        } else {
            dp[0][i] = 1;
        }

        if(arr[i] <= arr[i-1]){
            dp[1][i] = dp[1][i-1]+1;
        } else {
            dp[1][i] = 1;
        }

        answer = Math.max(answer, dp[0][i], dp[1][i]);
    }
    console.log(answer);
}());