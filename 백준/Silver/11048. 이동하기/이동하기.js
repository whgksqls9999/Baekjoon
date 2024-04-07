const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const [N, M] = input[0].split(" ").map(Number);

    const dp = Array(N + 1)
        .fill(0)
        .map((it) => Array(M + 1).fill(0));

    const arr = [Array(M + 1).fill(0)].concat(
        Array(N)
            .fill(0)
            .map((it, idx) => [0].concat(input[idx + 1].split(" ").map(Number)))
    );

    for (let i = 1; i < arr.length; i++) {
        for (let j = 1; j < arr[i].length; j++) {
            dp[i][j] =
                Math.max(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) +
                arr[i][j];
        }
    }

    console.log(dp[N][M]);
})();
