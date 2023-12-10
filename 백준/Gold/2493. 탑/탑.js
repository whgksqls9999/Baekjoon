const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

let N = Number(input[0]);
let arr = input[1].split(" ").map((element) => Number(element));
let answer = Array(N).fill(0);

let stack = [];

for (let i = 0; i < N; ++i) {
  let cur = arr[i];

  if (stack.length === 0) {
    answer[i] = 0;
  } else {
    let peek = stack[stack.length - 1];
    if (peek[1] >= cur) {
      answer[i] = peek[0] + 1;
    } else {
      while (stack.length !== 0 && stack[stack.length - 1][1] < cur) {
        stack.pop();
      }
      if (stack.length === 0) {
        answer[i] = 0;
      } else {
        answer[i] = stack[stack.length - 1][0] + 1;
      }
    }
  }

  stack.push([i, cur]);
}

console.log(answer.join(" "));
