const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let [x, y, w, h] = input[0].split(" ").map((element) => Number(element));

  let arr = [h - y, w - x, x, y];

  let min = 1001;
  for (element of arr) {
    if (min > element) {
      min = element;
    }
  }

  console.log(min);
}
