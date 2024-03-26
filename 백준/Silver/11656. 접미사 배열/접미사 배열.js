const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    const word = input[0];

    let arr = [];

    for (let i = 0; i < word.length; i++) {
        arr.push(word.substring(i));
    }

    arr.sort();

    console.log(arr.join("\n"));
})();
