const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const dp = Array(2).fill(0);
dp[0] = 1;
dp[1] = 1;
const MOD = 1_000_000_007;

(function solution() {
  let cur;
  for (let i = 3; i <= N; ++i) {
    dp[i % 2] = (dp[0] + dp[1]) % MOD;
    cur = i % 2;
  }
  console.log(`${dp[cur]} ${(N - 2) % MOD}`);
})();
