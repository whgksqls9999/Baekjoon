const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// 초기 설정

(function solution() {
  const answer = [];
  for (let i = 0; i < input.length - 1; ++i) {
    const cur = input[i].split(" ").map((element) => Number(element));

    let sum = cur.reduce((acc, cur) => acc + cur, 0);
    let max = Math.max(...cur);
    if (sum - max <= max) {
      answer.push("Invalid");
      continue;
    }

    // cur.sort((a, b) => a - b);
    // if (cur[0] + cur[1] <= cur[2]) {
    //   answer.push("Invalid");
    //   continue;
    // }

    const set = new Set(cur);
    if (set.size === 1) {
      answer.push("Equilateral");
    } else if (set.size === 2) {
      answer.push("Isosceles");
    } else {
      answer.push("Scalene");
    }
  }
  console.log(answer.join("\n"));
})();
