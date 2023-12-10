const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let arr = Array(10).fill(0);

  let answer = 0;
  for (let i = 0; i < input[0].length; ++i) {
    let cur = Number(input[0][i]);

    if (cur === 9 || cur === 6) {
      if (arr[6] > arr[9]) {
        ++arr[9];
      } else if (arr[6] < arr[9]) {
        ++arr[6];
      } else {
        ++arr[cur];
      }
    } else {
      ++arr[cur];
    }

    if (answer < arr[cur]) {
      answer = arr[cur];
    }
  }

  console.log(answer);
}
