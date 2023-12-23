//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const N = BigInt(input[0]);

  console.log(String(N * N * N));
  console.log(3);
})();
