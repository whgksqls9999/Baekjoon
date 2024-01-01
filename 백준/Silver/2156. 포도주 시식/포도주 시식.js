
//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const arr = input.slice(1, input.length).map(Number);
const dp = Array(arr.length).fill(0);

dp[0] = arr[0];
let answer = dp[0];

if (N > 1) {
  dp[1] = arr[1] + dp[0];
  answer = Math.max(dp[1], answer);
}

if (N > 2) {
  dp[2] = Math.max(arr[0] + arr[1], arr[0] + arr[2], arr[1] + arr[2]);
  answer = Math.max(dp[2], answer);
}

(function solution() {
  for (let i = 3; i < arr.length; i++) {
    dp[i] = Math.max(
      arr[i] + arr[i - 1] + dp[i - 3],
      arr[i] + dp[i - 2],
      dp[i - 1]
    );
    answer = Math.max(answer, dp[i]);
  }
  console.log(answer);
})();
