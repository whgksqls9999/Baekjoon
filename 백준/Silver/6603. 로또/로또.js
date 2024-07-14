const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;

function dfs(depth, idx, arr, result, perm) {
	if (depth === 6) {
		result.push(perm.trim());
		return;
	}

	for (let i = idx; i < arr.length; i++) {
		dfs(depth + 1, i + 1, arr, result, perm + ` ${arr[i]}`);
	}
}

function solution() {
	let answer = [];

	let cur = input[idx++].split(' ').map(Number);
	for (let i = 0; i < input.length - 1; i++) {
		const tmp = cur.slice(1);
		const result = [];

		dfs(0, 0, tmp, result, '');
		answer.push(result.join('\n'));
		answer.push('');
		cur = input[idx++].split(' ').map(Number);
	}

	console.log(answer.join('\n').trim());
}

solution();
