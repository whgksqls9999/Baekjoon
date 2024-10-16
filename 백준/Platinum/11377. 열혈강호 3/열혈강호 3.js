'use strict';

const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

function solution() {
	let idx = 0;
	const [N, M, K] = input[idx++].split(' ').map(Number);

	const edges = Array(N + 1)
		.fill()
		.map(() => []);
	const matching = Array(M + 1).fill(-1);

	for (let i = 0; i < N; i++) {
		const [_, ...arr] = input[idx++].split(' ').map(Number);
		edges[i + 1] = arr;
	}

	for (let i = 1; i <= N; i++) {
		const used = Array(M + 1).fill(false);
		dfs(i, matching, edges, used);
	}

	for (let i = 1, check = 0; i <= N && check < K; i++) {
		const used = Array(M + 1).fill(false);
		check += dfs(i, matching, edges, used);
	}

	const answer = matching.filter((it) => it !== -1).length;

	return answer;
}

function dfs(cur, matching, edges, used) {
	for (let next of edges[cur]) {
		if (used[next]) continue;
		used[next] = true;

		if (
			matching[next] === -1 ||
			dfs(matching[next], matching, edges, used)
		) {
			matching[next] = cur;
			return true;
		}
	}

	return false;
}

console.log(solution());
