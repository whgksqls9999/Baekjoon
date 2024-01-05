const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
let check = 0;
(function solution() {
  cal(N, N);
  console.log(check + " " + N ** 2);
})();

function cal(r, c) {
  return cal2(r, c);
}

function cal2(i, j) {
  if (i === 0 || j === 0) {
    ++check;
    return;
  }
  return cal(i - 1, j) + cal(i, j - 1);
}
