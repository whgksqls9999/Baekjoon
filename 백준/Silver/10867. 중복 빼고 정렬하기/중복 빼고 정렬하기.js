const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const N = Number(input[0]);

    const arr = Array(2001).fill(0);

    const numArr = input[1].split(" ").map(Number);
    for (let i of numArr) {
        arr[i + 1000]++;
    }

    let answer = [];
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] > 0) {
            answer.push(i - 1000);
        }
    }
    console.log(answer.join(" "));
})();
