const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const [A, B] = input[idx++].split(' ').map(Number);
	const N = Number(input[idx++]);
	const arr = input.slice(idx).map(Number);

	answer = Math.abs(A - B);

	for (let i of arr) {
		answer = Math.min(answer, 1 + Math.abs(i - B));
	}

	return answer;
}

console.log(solution());
