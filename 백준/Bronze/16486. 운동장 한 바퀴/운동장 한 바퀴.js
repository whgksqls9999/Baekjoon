const input = require('fs')
	.readFileSync(0, 'utf-8')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let M = +input[idx++];

	return (M * 2 * 3.141592 + N * 2).toFixed(6);
}

console.log(solution());
