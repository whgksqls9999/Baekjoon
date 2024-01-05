const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const arr = Array(N)
  .fill(0)
  .map((i, idx) => input[idx + 1].split(" ").map(Number));
const dp = Array(N)
  .fill(0)
  .map(() => Array(N).fill(BigInt(0)));
dp[0][0] = BigInt(1);

(function solution() {
  for (let i = 0; i < N; ++i) {
    for (let j = 0; j < N; ++j) {
      if (dp[i][j] === 0) continue;
      let val = arr[i][j];
      for (let k = 0; k < 2; ++k) {
        let nr = i + val * k;
        let nc = j + val * (1 - k);
        if ((nr !== i || nc !== j) && nr >= 0 && nr < N && nc >= 0 && nc < N) {
          dp[nr][nc] += dp[i][j];
        }
      }
    }
  }
  console.log(dp[N - 1][N - 1].toString());
})();
