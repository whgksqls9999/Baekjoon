const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const [N, M] = input[idx++].split(' ').map(Number);
	const arr = input[idx++].split(' ').map(Number);

	for (let i = 1; i < N; i++) {
		arr[i] += arr[i - 1];
	}

	for (let i = M - 1; i < N; i++) {
		answer = Math.max(answer, arr[i] - (arr[i - M] ?? 0));
	}

	return answer;
}

console.log(solution());
