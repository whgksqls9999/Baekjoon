const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// 초기 설정

(function solution() {
  const cur = input[0].split(" ").map(Number);
  cur.sort((a, b) => a - b);

  console.log(cur[0] + cur[1] + Math.min(cur[2], cur[0] + cur[1] - 1));
})();
