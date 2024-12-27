const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const N = +input[idx++];
	const regexp = input[idx++].trim().replace('*', '[a-z]*');
	const regex = new RegExp('^' + regexp + '$');

	for (let i = 0; i < N; i++) {
		const word = input[idx++].trim();

		answer.push(regex.test(word) ? 'DA' : 'NE');
	}

	return answer.join('\n');
}

console.log(solution());
