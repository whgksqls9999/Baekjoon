const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map(Number);
input.pop(); // 맨 끝 -1 제거

console.log(
  input
    .map(function solution(num) {
      const divisor = [1]; // 약수에 항상 1이 있으므로

      for (let i = 2; i < num; i++) {
        // 제곱근 범위
        if (num % i === 0) {
          divisor.push(i); // 약수 i
        }
      }
      if (divisor.reduce((acc, cur) => acc + cur, 0) === num) {
        // 완전수
        return `${num} = ` + divisor.join(" + ");
      } else {
        return `${num} is NOT perfect.`;
      }
    })
    .join("\n")
);
