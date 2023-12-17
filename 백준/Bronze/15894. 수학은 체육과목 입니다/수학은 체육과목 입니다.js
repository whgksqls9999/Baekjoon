const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
  console.log(Number(input[0]) * 4);
})();
