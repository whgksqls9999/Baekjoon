const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    const answer = [];
    for (let i = 1; i < input.length; i++) {
        const word = input[i].split("");

        answer.push(word.reverse().join(""));
    }
    console.log(answer.join("\n"));
})();
