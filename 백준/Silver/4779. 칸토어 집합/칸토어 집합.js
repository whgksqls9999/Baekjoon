// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// 초기 설정
let answer = [];

(function solution() {
  for (let i = 0; i < input.length; ++i) {
    const cur = Number(input[i]);
    doRecursion(3 ** cur, 0);
    answer.push("\n");
  }
  console.log(answer.join("").trim());
})();

function doRecursion(n, order) {
  if (n === 1) {
    let word = "-";
    if (order % 2 !== 0) {
      word = " ";
    }
    answer.push(word);
    return;
  }

  if (order % 2 !== 0) {
    for (let i = 0; i < n; ++i) {
      answer.push(" ");
    }
    return;
  }

  for (let i = 0; i < 3; ++i) {
    doRecursion(n / 3, i);
  }
}
