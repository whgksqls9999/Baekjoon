const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = -100 * 101;

	const [N, K] = input[idx++].split(' ').map(Number);
	const arr = input[idx++].split(' ').map(Number);
	for (let i = 1; i < N; i++) {
		arr[i] = arr[i - 1] + arr[i];
	}

	for (let i = K - 1; i < N; i++) {
		let sum = arr[i] - (arr[i - K] ?? 0);
		answer = Math.max(sum, answer);
	}

	return answer;
}

console.log(solution());
