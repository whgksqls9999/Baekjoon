const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const N = Number(input[0]);
    const MOD = 9901;

    const dp = Array(N + 1)
        .fill(0)
        .map((it) => Array(3).fill(0));

    dp[1] = [1, 1, 1];

    for (let i = 2; i <= N; i++) {
        dp[i][0] = dp[i - 1].reduce((prev, cur) => prev + cur, 0) % MOD;
        dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
        dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
    }

    console.log(dp[N].reduce((prev, cur) => prev + cur, 0) % MOD);
})();
