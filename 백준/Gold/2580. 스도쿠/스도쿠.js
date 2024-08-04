'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let arr = input.slice(input).map((it) => it.split(' ').map(Number));

	const visited = new Map();
	visited.row = Array(10)
		.fill()
		.map(() => Array(10).fill(false));
	visited.column = Array(10)
		.fill()
		.map(() => Array(10).fill(false));
	visited.zone = Array(10)
		.fill()
		.map(() => Array(10).fill(false));
	const zero = [];

	for (let i = 0; i < arr.length; i++) {
		for (let j = 0; j < arr.length; j++) {
			if (arr[i][j] === 0) {
				zero.push([i, j]);
				continue;
			}
			visited.row[i][arr[i][j]] = true;
			visited.column[j][arr[i][j]] = true;
			visited.zone[Math.floor(i / 3) * 3 + Math.floor(j / 3)][
				arr[i][j]
			] = true;
		}
	}

	let answer;

	function dfs(arr, visited, zero, depth) {
		if (zero.length === depth) {
			answer = arr.map((it) => it.join(' ')).join('\n');
			return;
		}

		let [r, c] = zero[depth];

		for (let i = 1; i <= 9; i++) {
			if (visited.row[r][i]) continue;
			if (visited.column[c][i]) continue;
			if (visited.zone[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i])
				continue;
			visited.row[r][i] = true;
			visited.column[c][i] = true;
			visited.zone[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i] = true;
			arr[r][c] = i;

			dfs(arr, visited, zero, depth + 1);

			if (answer) return;

			visited.row[r][i] = false;
			visited.column[c][i] = false;
			visited.zone[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i] = false;
			arr[r][c] = 0;
		}
	}

	dfs(arr, visited, zero, 0);

	return answer;
}

console.log(solution());
