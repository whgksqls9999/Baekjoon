//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  console.log(Math.ceil(Number(input[0]) / 5));
})();
