const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const [A, B] = input[idx++].split(' ').map(Number);

	answer.push(A / B);
	return answer.join('');
}

console.log(solution());
