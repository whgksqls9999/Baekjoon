//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const [a, b] = input[0].split(" ").map(Number);
  const answer = [];

  let num = a * b;
  answer.push(
    ...input[1].split(" ").map((element) => {
      return element - num;
    })
  );

  console.log(answer.join(" "));
})();
