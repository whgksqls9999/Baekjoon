const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const N = Number(input[0]);

  const dp = new Array(1000000).fill(0);
  dp[1] = 1;

  for (let i = 2; i <= N; ++i) {
    dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
  }

  console.log(dp[N]);
})();
