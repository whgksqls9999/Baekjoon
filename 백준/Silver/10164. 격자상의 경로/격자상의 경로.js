const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const [N, M, K] = input[0].split(" ").map(Number);
  const dp = Array(N + 1)
    .fill(0)
    .map(() => Array(M + 1).fill(0));

  // let r = Math.floor(K / M) + 1;
  // let c = K % M;

  let r = Math.ceil(K / M);
  let c = K % M === 0 ? M : K % M;

  if (K === 0) {
    [r, c] = [N, M];
  }

  for (let i = 1; i <= r; ++i) {
    for (let j = 1; j <= c; ++j) {
      if (i === 1 && j === 1) {
        dp[i][j] = 1;
        continue;
      }
      dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    }
  }

  for (let i = r; i <= N; ++i) {
    for (let j = c; j <= M; ++j) {
      dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    }
  }
  console.log(dp[N][M]);
})();
