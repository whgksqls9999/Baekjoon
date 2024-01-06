const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const dp = Array(1002)
  .fill(0)
  .map(() => Array(1002).fill(0));
const arr = Array(1002)
  .fill(0)
  .map(() => Array(1002).fill(0));
for (let i = 1; i <= N; ++i) {
  const cur = input[i].split(" ").map(Number);
  for (let j = 1; j <= N; ++j) {
    arr[i][j] = cur[j - 1];
  }
}
const [r, c] = input[N + 1].split(" ").map(Number);

(function solution() {
  const answer = [];
  for (let i = 1; i <= r; ++i) {
    for (let j = 1; j <= c; ++j) {
      let num1 = arr[i][j] + dp[i][j - 1];
      let num2 = arr[i][j] + dp[i - 1][j];
      if (num1 > num2) {
        dp[i][j] = num1;
      } else {
        dp[i][j] = num2;
      }
    }
  }
  for (let i = r; i <= N; ++i) {
    for (let j = c; j <= N; ++j) {
      let num1 = arr[i][j] + dp[i][j - 1];
      let num2 = arr[i][j] + dp[i - 1][j];
      if (num1 > num2) {
        dp[i][j] = num1;
      } else {
        dp[i][j] = num2;
      }
    }
  }
  answer.push(dp[N][N]);

  dp[r][c] = 0;
  for (let i = 1; i <= N; ++i) {
    for (let j = 1; j <= N; ++j) {
      if (i === r && j === c) continue;

      if (dp[i][j - 1] === 0 && dp[i - 1][j] === 0) {
        if (i !== 1 || j !== 1) {
          dp[i][j] = 0;
          continue;
        }
      }

      dp[i][j] = arr[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
    }
  }
  answer.push(dp[N][N]);
  console.log(answer.join(" "));
})();
