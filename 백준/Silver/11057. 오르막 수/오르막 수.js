const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const N = Number(input[0]);
    const dp = Array(N + 1)
        .fill(0)
        .map((it) => Array(10).fill(1));

    DFS(0, 1, N, dp);

    console.log(dp[N][0]);
})();

function DFS(num, depth, limit, dp) {
    if (depth === limit + 1) {
        return;
    }

    for (let i = 0; i < 10; i++) {
        dp[depth][i] =
            dp[depth - 1].slice(i).reduce((prev, cur) => prev + cur, 0) % 10007;
    }

    DFS(num, depth + 1, limit, dp);
}
