const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const N = +input[idx++];
	const regex = /^[A-F]?A+F+C+[A-F]?$/;

	for (let i = 0; i < N; i++) {
		const word = input[idx++].trim();

		answer.push(regex.test(word) ? 'Infected!' : 'Good');
	}

	return answer.join('\n');
}

console.log(solution());
