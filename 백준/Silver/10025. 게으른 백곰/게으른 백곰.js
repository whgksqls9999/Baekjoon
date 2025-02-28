const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const [N, K] = input[idx++].split(' ').map(Number);
	const arr = Array(1_000_001).fill(0);

	for (let i = 0; i < N; i++) {
		const [g, x] = input[idx++].split(' ').map(Number);
		arr[x] = g;
	}

	let answer = 0;
	for (let i = 1; i < arr.length; i++) {
		arr[i] = arr[i - 1] + arr[i];
		answer = Math.max(answer, arr[i] - (arr[i - 1 - K * 2] ?? 0));
	}

	return answer;
}

console.log(solution());
