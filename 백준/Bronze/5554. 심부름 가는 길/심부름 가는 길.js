const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  let answer = input.reduce((pre, cur) => Number(pre) + Number(cur), 0);
  console.log(Math.floor(answer / 60));
  console.log(answer % 60);
})();
