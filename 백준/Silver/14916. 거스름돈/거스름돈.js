//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  let N = Number(input[0]);
  const dp = Array(N + 1).fill(-1);
  dp[2] = 1;
  dp[5] = 1;

  for (let i = 3; i <= N; ++i) {
    if (dp[i - 2] !== -1 && dp[i - 5] && dp[i - 5] !== -1) {
      dp[i] = Math.min(dp[i - 2] + 1, dp[i - 5] + 1);
    } else if (dp[i - 2] !== -1) {
      dp[i] = dp[i - 2] + 1;
    } else if (dp[i - 5] && dp[i - 5] !== -1) {
      dp[i] = dp[i - 5] + 1;
    }
  }
  console.log(dp[N]);
})();
