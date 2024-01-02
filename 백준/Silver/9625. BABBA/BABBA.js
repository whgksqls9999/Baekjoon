//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);

(function solution() {
  let dp = Array(N + 1)
    .fill(0)
    .map(() => Array(2).fill(0));
  dp[0] = [1, 0];
  dp[1] = [0, 1];
  for (let i = 2; i <= N; ++i) {
    dp[i] = [dp[i - 1][0] + dp[i - 2][0], dp[i - 1][1] + dp[i - 2][1]];
  }
  console.log(dp[N].join(" "));
})();
