const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	const N = Number(input[idx++]);
	const dp = Array(N + 1).fill(-1);

	answer.push(recur(N, dp));

	return answer.join('\n');
}

function recur(n, dp) {
	if (n === 0) return BigInt(1);
	if (dp[n] !== -1) return BigInt(dp[n]);

	let cur = BigInt(0);
	for (let i = 0; i < n; i++) {
		cur += recur(i, dp) * recur(n - i - 1, dp);
	}

	return (dp[n] = BigInt(cur));
}

console.log(solution());
