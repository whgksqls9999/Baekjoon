const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;

	let [x1, y1, r1] = input[idx++].split(' ').map(Number);
	let [x2, y2, r2] = input[idx++].split(' ').map(Number);

	return Math.sqrt(Math.abs(y2 - y1) ** 2 + Math.abs(x2 - x1) ** 2) < r1 + r2
		? 'YES'
		: 'NO';
}

console.log(solution());
