const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx];
const arr = N.toString().split('').map(Number);
const visited = Array(10).fill(0);
for (let i of arr) {
	visited[i]++;
}

function dfs(arr, visited, num, depth) {
	if (depth === arr.length) {
		if (+num <= N) return Number.MAX_SAFE_INTEGER;

		return +num;
	}

	let result = Number.MAX_SAFE_INTEGER;

	for (let i of arr) {
		if (visited[i] > 0) {
			visited[i]--;
			result = Math.min(dfs(arr, visited, num + i, depth + 1), result);
			visited[i]++;
		}
	}

	return result;
}
function solution() {
	let answer = dfs(arr, visited, '', 0);

	console.log(answer === Number.MAX_SAFE_INTEGER ? 0 : answer);
}

solution();
