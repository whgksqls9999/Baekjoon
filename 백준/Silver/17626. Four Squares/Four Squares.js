'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const N = +input[idx++];

	const dp = Array(N + 1).fill(0);
	dp[1] = 1;

	for (let i = 2; i <= N; i++) {
		let minPow = Number.MAX_SAFE_INTEGER;

		for (let j = 1; j ** 2 <= i; j++) {
			minPow = Math.min(dp[i - j ** 2], minPow);
		}
		dp[i] = minPow + 1;
	}

	return dp[N];
}

console.log(solution());
