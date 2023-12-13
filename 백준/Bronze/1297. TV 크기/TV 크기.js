const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let answer = [];
  let [D, H, W] = input[0].split(" ").map((element) => Number(element));
  let sum = H ** 2 + W ** 2;
  answer.push(Math.floor(Math.sqrt((D ** 2 * H ** 2) / sum)));
  answer.push(Math.floor(Math.sqrt((D ** 2 * W ** 2) / sum)));
  console.log(answer.join(" "));
}
