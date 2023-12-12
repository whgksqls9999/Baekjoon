const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let N = Number(input[0]);

  let answer = [];
  answer.push((N ** 2 * Math.PI).toFixed(6));
  answer.push((N ** 2 * 2).toFixed(6));

  console.log(answer.join("\n"));
}
