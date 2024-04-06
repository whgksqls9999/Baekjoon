const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const [N, K] = input[0].split(" ").map(Number);

    const dp = Array(N + 1)
        .fill(0)
        .map((it) => Array(N + 1).fill(0));

    for (let i = 0; i < dp.length; i++) {
        dp[i][0] = 1;
        for (let j = 1; j < i + 1; j++) {
            dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
        }
    }

    console.log(dp[N][K]);
})();
