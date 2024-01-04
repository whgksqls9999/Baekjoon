const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const dp = Array(N + 1).fill(0);

(function solution() {
  const arr = [1, 3, 4];
  dp[1] = 1;
  dp[2] = 0;
  dp[3] = 1;
  dp[4] = 1;
  dp[5] = 1;
  for (let i = 6; i <= N; ++i) {
    arr.forEach((element) => {
      if (dp[i - element] === 0) {
        dp[i] = 1;
      }
    });
  }
  console.log(dp[N] === 1 ? "SK" : "CY");
})();
