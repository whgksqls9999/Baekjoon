const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const [N, M, K] = input[0].split(' ').map(Number);

function solution() {
	console.log(`${~~(K / M)} ${~~(K % M)}`);
}

solution();
