const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;
	const N = Number(input[idx++]);
	for (let i = 0; i < N; i++) {
		answer += Number(input[idx++]);
	}

	return answer - (N - 1);
}

console.log(solution());
