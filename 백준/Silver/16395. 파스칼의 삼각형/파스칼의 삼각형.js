const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, K] = input[0].split(" ").map(Number);
const dp = Array(N)
  .fill(0)
  .map(() => Array(N).fill(0));

(function solution() {
  for (let i = 0; i < N; ++i) {
    dp[i][0] = 1;
    for (let j = 1; j < i + 1; ++j) {
      dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
    }
  }
  console.log(dp[N - 1][K - 1]);
})();
