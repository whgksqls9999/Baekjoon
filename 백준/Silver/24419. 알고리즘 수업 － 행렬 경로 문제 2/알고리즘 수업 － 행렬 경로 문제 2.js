const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const dp = Array(N)
  .fill(0)
  .map(() => Array(N).fill(BigInt(0)));
dp[N - 1][N - 1] = BigInt(1);

(function solution() {
  for (let i = N - 1; i >= 0; --i) {
    dp[i][N - 1] = BigInt(1);
    dp[N - 1][i] = BigInt(1);
    for (let j = N - 1; j >= 0; --j) {
      for (let k = 0; k < 2; ++k) {
        let nr = i + k;
        let nc = j + (1 - k);
        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
          dp[i][j] += dp[nr][nc];
        }
      }
    }
  }
  console.log(
    `${(dp[0][0] * BigInt(2)) % BigInt(1_000_000_007)} ${
      N ** 2 % 1_000_000_007
    }`
  );
})();
