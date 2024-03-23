const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    const [N, M] = input[0].split(" ").map(Number);

    const firstArr = input[1].split(" ").map(Number);
    const secondArr = input[2].split(" ").map(Number);

    const answer = [];
    let [left, right] = [0, 0];

    while (left < firstArr.length && right < secondArr.length) {
        if (firstArr[left] > secondArr[right]) {
            answer.push(secondArr[right++]);
            continue;
        }
        answer.push(firstArr[left++]);
    }

    while (left < firstArr.length) {
        answer.push(firstArr[left++]);
    }

    while (right < secondArr.length) {
        answer.push(secondArr[right++]);
    }

    console.log(answer.join(" "));
})();
