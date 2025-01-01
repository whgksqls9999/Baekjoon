const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const [A, B] = input[idx++].split(' ').map(Number);
	const M = (B - A) / 400;

	answer.push(1 / (1 + 10 ** M));

	return answer.join('');
}

console.log(solution());
