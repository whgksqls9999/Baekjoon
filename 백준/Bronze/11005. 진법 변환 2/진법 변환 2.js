const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  let [N, M] = input[0].split(" ").map(Number);

  const answer = [];
  while (N >= M) {
    let cur = N % M;
    answer.push(cur <= 9 ? cur : String.fromCharCode(cur + 55));
    N = Math.floor(N / M);
  }
  answer.push(N <= 9 ? N : String.fromCharCode(N + 55));
  console.log(answer.reverse().join(""));
})();
