const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

//초기 설정
let N = Number(input[0]);

(function solution() {
  console.log(N ** 2 + "\n" + 2);
})();
