//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");


const N = Number(input[0]);
const dp = Array(N + 1)
  .fill(0)
  .map(() => Array(10).fill(0));
for (let i = 0; i < 10; ++i) {
  dp[1][i] = 1;
}
dp[1][0] = 0;

(function solution() {
  for (let i = 2; i <= N; ++i) {
    dp[i][0] = dp[i - 1][1];
    dp[i][9] = dp[i - 1][8];
    for (let j = 1; j <= 8; ++j) {
      dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
    }
  }
  let answer = dp[N].reduce((pre, cur) => pre + cur, 0);
  console.log(answer % 1_000_000_000);
})();
