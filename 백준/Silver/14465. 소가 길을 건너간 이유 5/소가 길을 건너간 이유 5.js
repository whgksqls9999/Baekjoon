const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;

	const [N, K, B] = input[idx++].split(' ').map(Number);
	const arr = Array(N + 1).fill(0);

	let answer = N;

	for (let i = 0; i < B; i++) {
		let cur = Number(input[idx++]);
		arr[cur] = 1;
	}

	for (let i = 1; i <= N; i++) {
		arr[i] += arr[i - 1];
	}

	for (let i = K; i <= N; i++) {
		answer = Math.min(answer, arr[i] - arr[i - K]);
	}

	return answer;
}

console.log(solution());
