//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const dp = Array(N + 1).fill(BigInt(0));
dp[1] = BigInt(1);

(function solution() {
  for (let i = 2; i <= N; ++i) {
    dp[i] = dp[i - 1] + dp[i - 2];
  }
  console.log(dp[N].toString());
})();
