const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let [a, b, c] = input.map((element) => Number(element));

  let sum = 0;
  [a, b, c].forEach((element) => {
    sum += element;
  });

  let set = new Set();
  [a, b, c].forEach((element) => {
    set.add(element);
  });

  let answer = "";

  if (sum !== 180) {
    answer = "Error";
  } else {
    if (set.size === 2) {
      answer = "Isosceles";
    } else if (set.size === 3) {
      answer = "Scalene";
    } else {
      answer = "Equilateral";
    }
  }

  console.log(answer);
}
