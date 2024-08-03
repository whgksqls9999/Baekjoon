'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function dfs(arr, taste, N, depth) {
	if (depth === N + 1) {
		if (taste.join(' ') === '1 0') {
			return Number.MAX_SAFE_INTEGER;
		}
		return Math.abs(taste[0] - taste[1]);
	}

	let result = Number.MAX_SAFE_INTEGER;

	result = Math.min(
		dfs(
			arr,
			[taste[0] * arr[depth][0], taste[1] + arr[depth][1]],
			N,
			depth + 1
		),
		dfs(arr, taste, N, depth + 1)
	);

	return result;
}
function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = [[1, 0]].concat(
		input.slice(idx).map((it) => it.split(' ').map(Number))
	);

	let answer = dfs(arr, [1, 0], N, 0);

	return answer;
}

console.log(solution());
