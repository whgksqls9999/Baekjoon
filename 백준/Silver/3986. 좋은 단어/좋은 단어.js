const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    let N = Number(input[0]);

    let answer = 0;
    for (let i = 1; i <= N; i++) {
        const word = input[i].trim();
        const arr = [];

        for (let j = 0; j < word.length; j++) {
            if (arr.length === 0 || arr[arr.length - 1] !== word[j]) {
                arr.push(word[j]);
            } else if (arr[arr.length - 1] === word[j]) {
                arr.pop();
            }
        }

        if (arr.length === 0) {
            answer++;
        }
    }
    console.log(answer);
})();
