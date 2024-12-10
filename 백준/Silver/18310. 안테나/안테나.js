const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	arr.sort((a, b) => a - b);

	return arr[Math.floor((arr.length - 1) / 2)];
}

console.log(solution());
