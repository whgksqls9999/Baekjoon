const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function filter(arr, i, j) {
	const filtered_array = [];

	const limit_i = i + 3;
	const limit_j = j + 3;

	for (let a = i; a < limit_i; a++) {
		for (let b = j; b < limit_j; b++) {
			filtered_array.push(arr[a][b]);
		}
	}

	filtered_array.sort((a, b) => a - b);

	return filtered_array[4];
}

function solution() {
	let idx = 0;
	let answer = 0;

	const [R, C] = input[idx++].split(' ').map(Number);
	const arr = input
		.slice(idx++, input.length - 1)
		.map((it) => it.split(' ').map(Number));
	const T = Number(input[input.length - 1]);

	for (let i = 0; i <= R - 3; i++) {
		for (let j = 0; j <= C - 3; j++) {
			if (filter(arr, i, j) >= T) {
				answer++;
			}
		}
	}

	return answer;
}

console.log(solution());
