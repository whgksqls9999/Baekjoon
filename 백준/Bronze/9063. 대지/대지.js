const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const N = Number(input[0]);
const arr = [10000, -10000, 10000, -10000];

for (let i = 0; i < N; ++i) {
  let cur = input[i + 1].split(" ").map((element) => Number(element));

  if (arr[0] > cur[0]) {
    arr[0] = cur[0];
  }
  if (arr[1] < cur[0]) {
    arr[1] = cur[0];
  }
  if (arr[2] > cur[1]) {
    arr[2] = cur[1];
  }
  if (arr[3] < cur[1]) {
    arr[3] = cur[1];
  }
}

(function solution() {
  console.log((arr[1] - arr[0]) * (arr[3] - arr[2]));
})();
