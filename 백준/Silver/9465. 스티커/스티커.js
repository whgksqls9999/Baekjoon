const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const T = Number(input[0]);
    const answer = [];

    for (let tc = 0; tc < T; tc++) {
        const N = Number(input[tc * 3 + 1]);

        const arr = Array(3)
            .fill(0)
            .map((it) => Array(N).fill(0));

        for (let i = 0; i < 2; i++) {
            arr[i + 1] = [0, 0].concat(
                input[tc * 3 + 2 + i].split(" ").map(Number)
            );
        }

        const dp = Array(3)
            .fill(0)
            .map(() => Array(N + 2).fill(0));

        let score = 0;

        dp[1][2] = arr[1][2];
        dp[2][2] = arr[2][2];

        for (let j = 2; j < dp[0].length; j++) {
            dp[1][j] = Math.max(dp[2][j - 1], dp[2][j - 2]) + arr[1][j];
            dp[2][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + arr[2][j];
        }

        score = Math.max(dp[1][N + 1], dp[2][N + 1]);

        answer.push(score);
    }

    console.log(answer.join("\n"));
})();
