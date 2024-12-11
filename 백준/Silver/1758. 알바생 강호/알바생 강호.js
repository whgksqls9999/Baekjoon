const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const N = Number(input[idx++]);
	const arr = input.slice(idx++).map(Number);
	arr.sort((a, b) => b - a);

	for (let i = 0; i < N; i++) {
		answer += Math.max(arr[i] - i, 0);
	}

	return answer;
}

console.log(solution());
