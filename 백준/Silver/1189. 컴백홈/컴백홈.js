'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
let answer = 0;

function dfs(arr, R, C, K, cur, visited, depth) {
	if (cur.join(' ') === `0 ${C - 1}`) {
		if (depth === K) answer++;
		return;
	}

	for (let i = 0; i < dr.length; i++) {
		let nr = cur[0] + dr[i];
		let nc = cur[1] + dc[i];

		if (
			nr >= 0 &&
			nr < R &&
			nc >= 0 &&
			nc < C &&
			!visited[nr][nc] &&
			arr[nr][nc] !== 'T'
		) {
			visited[nr][nc] = true;
			dfs(arr, R, C, K, [nr, nc], visited, depth + 1);
			visited[nr][nc] = false;
		}
	}
}
function solution() {
	let idx = 0;
	let [R, C, K] = input[idx++].split(' ').map(Number);
	let arr = input.slice(idx).map((it) => it.trim().split(''));
	const visited = Array(R)
		.fill()
		.map(() => Array(C).fill(false));
	visited[R - 1][0] = true;

	dfs(arr, R, C, K, [R - 1, 0], visited, 1);

	return answer;
}

console.log(solution());
