const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const answer = [];
  for (let i = 0; i < 3; ++i) {
    for (let j = 0; j < 3; ++j) {
      if (i == 1 && j == 1) {
        answer.push(`:${input[0]}:`);
      } else {
        answer.push(":fan:");
      }
    }
    answer.push("\n");
  }
  console.log(answer.join(""));
})();
