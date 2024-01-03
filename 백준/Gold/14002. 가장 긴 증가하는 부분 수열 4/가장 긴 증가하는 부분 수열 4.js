const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
const N = Number(input[0]);
const arr = input[1].split(' ').map(Number);


(function solution(){
    const dp = Array(N).fill(0);
    dp[0] = 1;

    let max = 1;
    for (let i = 1; i < N; ++i){
        for (let j = 0; j < i; ++j){
            if(arr[j] < arr[i] && dp[j] > dp[i]){
                dp[i] = dp[j];
            }
        }
        ++dp[i];
        max = Math.max(max, dp[i]);
    }
    console.log(max);

    const answer = [];
    for (let i = N-1; i >= 0; --i){
        if(dp[i] === max){
            answer.push(arr[i]);
            --max;
        }
    }
    
    console.log(answer.reverse().join(' '));
}());