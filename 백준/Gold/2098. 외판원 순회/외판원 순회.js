const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

let answer = 0;
let INF = 1000000 * 16;

(function solution() {
    const N = Number(input[0]);
    const distArr = Array(N)
        .fill(0)
        .map(() => Array(N).fill(0));

    for (let i = 1; i <= N; i++) {
        distArr[i - 1] = input[i].split(" ").map(Number);
    }

    const dp = Array(N)
        .fill(0)
        .map(() => Array((1 << N) - 1).fill(-1));

    console.log(travel(distArr, dp, N, 0, 1));
})();

function travel(distArr, dp, N, cur, visited) {
    if (visited === (1 << N) - 1) {
        if (distArr[cur][0] === 0) {
            return INF;
        }

        return distArr[cur][0];
    }

    if (dp[cur][visited] !== -1) {
        return dp[cur][visited];
    }

    dp[cur][visited] = INF;

    for (let i = 0; i < distArr[cur].length; i++) {
        if (distArr[cur][i] !== 0) {
            if ((visited & (1 << i)) === 0) {
                dp[cur][visited] = Math.min(
                    dp[cur][visited],
                    travel(distArr, dp, N, i, visited | (1 << i)) +
                        distArr[cur][i]
                );
            }
        }
    }
    return dp[cur][visited];
}
