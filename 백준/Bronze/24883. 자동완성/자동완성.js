const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  let answer = "Naver Whale";

  ["N", "n"].forEach((element) => {
    if (input[0] === element) {
      answer = "Naver D2";
    }
  });

  console.log(answer);
})();
