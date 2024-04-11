const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const [N, M] = input[0].split(" ").map(Number);

    console.log(N * (M - 1) + 1);
})();
