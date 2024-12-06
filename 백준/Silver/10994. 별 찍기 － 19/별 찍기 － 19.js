const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;

	const N = Number(input[idx++]);

	const length = 1 + (N - 1) * 4;

	const arr = Array(length)
		.fill()
		.map(() => Array(length).fill(' '));

	draw(N, arr, 0, 0);

	return arr.map((it) => it.join('')).join('\n');
}

function draw(num, arr, r, c) {
	const length = 1 + (num - 1) * 4;

	if (num === 1) {
		arr[r][c] = '*';
		return;
	}

	for (let i = 0; i < length; i++) {
		arr[r][c + i] = '*';
		arr[r + i][c] = '*';
		arr[r + i][c + length - 1] = '*';
		arr[r + length - 1][c + i] = '*';
	}

	draw(num - 1, arr, r + 2, c + 2);
}

console.log(solution());
