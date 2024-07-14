const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx++];
const order = input[idx].split(' ').map(Number);

function dfs(answer, depth, arr) {
	if (arr.length === 1) {
		answer[depth].push(arr[0]);
		return;
	}

	let mid = Math.floor(arr.length / 2);
	dfs(answer, depth + 1, arr.slice(0, mid));
	dfs(answer, depth + 1, arr.slice(mid + 1, arr.length));
	answer[depth].push(arr[mid]);
}

function solution() {
	const answer = Array(N)
		.fill()
		.map(() => []);

	dfs(answer, 0, order);

	console.log(answer.map((it) => it.join(' ')).join('\n'));
}

solution();
