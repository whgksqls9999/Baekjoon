const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
// const dp = Array(2).fill(0);
// dp[0] = 1;
// dp[1] = 1;
let A = 1;
let B = 1;
const MOD = 1_000_000_007;

(function solution() {
  for (let i = 3; i <= N; ++i) {
    let tmp = A + B;
    if (tmp >= MOD) tmp -= MOD;
    A = B;
    B = tmp;
  }
  console.log(`${B} ${(N - 2) % MOD}`);
})();
