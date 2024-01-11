const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  let val = 1000 - Number(input[0]);
  let answer = 0;

  const arr = [500, 100, 50, 10, 5, 1];

  arr.forEach((element) => {
    const arr = cal(val, element);
    answer += arr[0];
    val = arr[1];
  });

  console.log(answer);
})();

function cal(value, price) {
  return [Math.floor(value / price), value % price];
}
