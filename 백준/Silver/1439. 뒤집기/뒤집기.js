const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    const word = input[0];

    const checkArr = [0, 0];

    let prev = word[0];
    for (let i = 1; i < word.length; i++) {
        if (prev !== word[i]) {
            checkArr[Number(prev)]++;
            prev = word[i];
        }
    }

    checkArr[Number(word[word.length - 1])]++;

    console.log(Math.min(...checkArr));
})();
