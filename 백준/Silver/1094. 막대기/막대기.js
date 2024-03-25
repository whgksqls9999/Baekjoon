const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    let length = 64;
    let N = Number(input[0]);
    let cnt = 0;
    while (N != 0) {
        if (N < length) {
            length >>= 1;
        } else {
            cnt++;
            N -= length;
            length >>= 1;
        }
    }
    console.log(cnt);
})();
