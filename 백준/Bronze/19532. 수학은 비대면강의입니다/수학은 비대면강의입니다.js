//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let [a, b, c, d, e, f] = input[0].split(" ").map(Number);

(function solution() {
  const answer = [];

  outer: for (let i = -999; i <= 999; ++i) {
    for (let j = -999; j <= 999; ++j) {
      if (a * i + b * j === c && d * i + e * j === f) {
        answer.push(i);
        answer.push(j);
        break outer;
      }
    }
  }

  console.log(answer.join(" "));
})();
