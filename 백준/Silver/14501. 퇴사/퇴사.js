// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

console.log(solution().toString().trim());

function solution() {
  let N = Number(input[0]);

  let table = Array(N);

  for (let i = 1; i <= N; ++i) {
    let [days, earn] = input[i].split(" ").map((element) => Number(element));

    table[i - 1] = [days, earn];
  }

  let dp = Array(N + 1).fill(0);

  for (let i = 0; i < N; ++i) {
    let [days, earn] = table[i];
    // if ((i > 0 && dp[i] === 0 && dp[i - 1] !== 0) || i + days > N) {
    //   dp[i] = Math.max(dp[i - 1], dp[i]);
    // }
    if (i > 0) {
      dp[i] = Math.max(dp[i - 1], dp[i]);
    }

    if (dp[i + days] < dp[i] + earn) {
      dp[i + days] = dp[i] + earn;
    }
  }

  // console.log(dp);

  let max = 0;
  for (let i = 0; i < dp.length; ++i) {
    if (max < dp[i]) {
      max = dp[i];
    }
  }

  return max;
}
