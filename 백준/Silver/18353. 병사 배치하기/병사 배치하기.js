const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const N = Number(input[0]);
    const arr = input[1].split(" ").map(Number);
    const dp = Array(arr.length).fill(1);

    for (let i = 1; i < arr.length; i++) {
        let now = arr[i];

        for (let j = 0; j < i; j++) {
            if (arr[j] > now) {
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
    }

    console.log(N - Math.max(...dp));
})();
