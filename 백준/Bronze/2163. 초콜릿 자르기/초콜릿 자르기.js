const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const [N, M] = input[idx++].split(' ').map(Number);

	return N * M - 1;
}

console.log(solution());
