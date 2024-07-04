const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let [N, arr] = input;
N = +N;
arr = [0].concat(arr.split(' ').map(Number));

const dp = Array(arr.length)
	.fill(0)
	.map(() => Array(N + 1).fill(0));

function solution() {
	let answer = 0;
	for (let i = 1; i < dp.length; i++) {
		for (let j = 1; j < dp[i].length; j++) {
			if (i > j) {
				dp[i][j] = dp[i - 1][j];
			} else {
				dp[i][j] = Math.max(
					dp[i][j - i] + arr[i],
					dp[i - 1][j - i] + arr[i],
					dp[i - 1][j]
				);
			}
		}
	}

	for (let i = 1; i < dp.length; i++) {
		answer = Math.max(answer, dp[i][N]);
	}

	console.log(dp[N][N]);
}

solution();
