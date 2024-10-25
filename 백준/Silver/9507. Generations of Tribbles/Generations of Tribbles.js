const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	const T = Number(input[idx++]);
	for (let i = 0; i < T; i++) {
		let n = Number(input[idx++]);

		const dp = Array(n + 1).fill(BigInt(-1));
		answer.push(koong(n, dp));
	}

	return answer.join('\n');
}

function koong(n, dp) {
	if (n < 2) return BigInt(1);
	if (n === 2) return BigInt(2);
	if (n === 3) return BigInt(4);
	if (dp[n] !== BigInt(-1)) return dp[n];

	return (dp[n] =
		koong(n - 1, dp) +
		koong(n - 2, dp) +
		koong(n - 3, dp) +
		koong(n - 4, dp));
}

console.log(solution());
