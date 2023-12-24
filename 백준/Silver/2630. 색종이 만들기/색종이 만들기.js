//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const arr = Array(N)
  .fill(0)
  .map((element, idx) => {
    return input[idx + 1].split(" ").map(Number);
  });
const answer = [0, 0];

(function solution() {
  quardTree(0, 0, N);
  console.log(answer.join("\n"));
})();

function quardTree(r, c, size) {
  let initialVal = arr[r][c];
  let isSame = true;

  outer: for (let i = r; i < r + size; ++i) {
    for (let j = c; j < c + size; ++j) {
      if (arr[i][j] !== initialVal) {
        isSame = false;
        break outer;
      }
    }
  }

  if (isSame) {
    ++answer[initialVal];
  } else {
    quardTree(r, c, size / 2);
    quardTree(r, c + size / 2, size / 2);
    quardTree(r + size / 2, c, size / 2);
    quardTree(r + size / 2, c + size / 2, size / 2);
  }
}
