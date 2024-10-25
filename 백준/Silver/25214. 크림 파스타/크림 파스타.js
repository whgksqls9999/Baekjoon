const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	const dp = Array(N).fill(0);

	let min = arr[0];
	let res = 0;
	for (let i = 0; i < N; i++) {
		if (arr[i] > min) {
			res = Math.max(res, arr[i] - min);
		} else if (arr[i] < min) {
			min = arr[i];
		}

		answer.push(res);
	}

	return answer.join(' ');
}

console.log(solution());
