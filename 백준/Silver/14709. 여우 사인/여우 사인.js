//초기 설정
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

(function solution() {
  const N = Number(input[0]);
  const nodes = Array(6);
  for (let i = 0; i < 6; ++i) {
    nodes[i] = new Set();
  }
  for (let i = 1; i <= N; ++i) {
    const cur = input[i].trim().split(" ");

    nodes[cur[0]].add(cur[1]);
    nodes[cur[1]].add(cur[0]);
  }

  let state = true;
  const needs = [1, 3, 4];

  if (nodes[2].size !== 0 || nodes[5].size !== 0) {
    state = false;
  } else {
    needs.forEach((element) => {
      if (
        nodes[element].size !== 2 ||
        nodes[element].has("2") ||
        nodes[element].has("5")
      ) {
        state = false;
      }
    });
  }

  if (state) {
    console.log("Wa-pa-pa-pa-pa-pa-pow!");
  } else {
    console.log("Woof-meow-tweet-squeek");
  }
})();
