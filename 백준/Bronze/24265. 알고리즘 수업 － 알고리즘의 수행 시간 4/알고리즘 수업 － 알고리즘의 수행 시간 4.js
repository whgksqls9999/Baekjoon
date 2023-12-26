//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const N = Number(input[0]);
  const answer = [];
  let sum = 0;

  for (let i = 1; i < N; ++i) {
    sum += i;
  }

  answer.push(sum);
  answer.push(2);

  console.log(answer.join("\n"));
})();
