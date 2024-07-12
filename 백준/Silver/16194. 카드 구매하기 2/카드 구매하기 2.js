const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let N = +input[idx++];
const arr = [0, ...input[idx].split(' ').map(Number)];
const dp = Array(N + 1)
	.fill()
	.map(() => Array(N + 1).fill(10000 * 1001));
for (let i = 1; i <= N; i++) {
	dp[1][i] = arr[1] * i;
	dp[i][0] = 0;
}
function solution() {
	for (let i = 2; i <= N; i++) {
		for (let j = 1; j <= N; j++) {
			if (i > j) {
				dp[i][j] = dp[i - 1][j];
			} else {
				dp[i][j] = Math.min(
					dp[i - 1][j - i] + arr[i],
					dp[i - 1][j],
					dp[i][j - i] + arr[i]
				);
			}
		}
	}
	console.log(dp[N][N]);
}

solution();
