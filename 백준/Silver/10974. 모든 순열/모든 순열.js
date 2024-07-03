const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const N = +input[0];

const arr = [];
for (let i = 1; i <= N; i++) {
	arr.push(i);
}

const used = Array(N + 1).fill(false);

let answer = '';
let cur = [];

function dfs(depth) {
	if (depth === N) {
		let tmp = '';

		for (let key of cur) {
			tmp += `${key} `;
		}

		answer += tmp.trim() + '\n';
		return;
	}

	for (let i = 1; i <= N; i++) {
		if (used[i]) continue;

		cur.push(i);
		used[i] = true;
		dfs(depth + 1);
		used[i] = false;
		cur.pop();
	}
}

function solution() {
	dfs(0);

	console.log(answer.trim());
}

solution();
