//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);
const dp = Array(2)
  .fill(0)
  .map(() => Array(N).fill(0));
dp[0][0] = 1;
dp[1][N - 1] = 1;

(function solution() {
  for (let i = 1; i < N; ++i) {
    let bigest = 0;
    let bigestIdx = i;
    for (let j = 0; j < i; ++j) {
      if (dp[0][j] > bigest && arr[j] < arr[i]) {
        bigest = dp[0][j];
        bigestIdx = j;
      }
    }
    dp[0][i] = dp[0][bigestIdx] + 1;
  }
  for (let i = N - 2; i >= 0; --i) {
    let bigest = 0;
    let bigestIdx = i;
    for (let j = N - 1; j > i; --j) {
      if (dp[1][j] > bigest && arr[j] < arr[i]) {
        bigest = dp[1][j];
        bigestIdx = j;
      }
    }
    dp[1][i] = dp[1][bigestIdx] + 1;
  }

  let answer = 0;
  for (let i = 0; i < N; ++i) {
    let cur = dp[0][i] + dp[1][i] - 1;
    if (cur > answer) {
      answer = cur;
    }
  }
  console.log(answer);
})();
