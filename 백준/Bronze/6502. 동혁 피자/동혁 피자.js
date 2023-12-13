const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

solution();

function solution() {
  let answer = [];
  let tc = 0;
  while (input[tc].length !== 1) {
    let [r, w, l] = input[tc].split(" ").map((element) => Number(element));

    let str = `Pizza ${tc + 1} `;

    if (Math.sqrt(w ** 2 + l ** 2) / 2 <= r) {
      str += "fits on the table.";
    } else {
      str += "does not fit on the table.";
    }

    answer.push(str);
    ++tc;
  }

  console.log(answer.join("\n"));
}
