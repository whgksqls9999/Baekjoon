const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
  let answer = [];
  answer.push(Number(input[0]));
  answer.push(1);
  console.log(answer.join("\n"));
})();
