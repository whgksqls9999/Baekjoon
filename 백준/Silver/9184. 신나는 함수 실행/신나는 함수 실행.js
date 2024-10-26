const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	let [a, b, c] = input[idx++].split(' ').map(Number);
	const dp = Array(101)
		.fill()
		.map(() =>
			Array(101)
				.fill()
				.map(() => Array(101).fill(-1))
		);

	while (!(a === -1 && b === -1 && c === -1)) {
		answer.push(
			`w(${a}, ${b}, ${c}) = ${recur(a + 50, b + 50, c + 50, dp)}`
		);

		[a, b, c] = input[idx++].split(' ').map(Number);
	}

	return answer.join('\n');
}

function recur(a, b, c, dp) {
	if (a <= 50 || b <= 50 || c <= 50) return 1;
	if (dp[a][b][c] !== -1) return dp[a][b][c];
	if (a > 70 || b > 70 || c > 70) return recur(70, 70, 70, dp);

	return (dp[a][b][c] =
		recur(a - 1, b, c, dp) +
		recur(a - 1, b - 1, c, dp) +
		recur(a - 1, b, c - 1, dp) -
		recur(a - 1, b - 1, c - 1, dp));
}

console.log(solution());
