const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function dfs(cur, cost, visited, nodes, depth, N, org) {
	if (depth === N) {
		if (nodes[cur][org] === 0) return 1_000_000 * 10;
		return cost + nodes[cur][org];
	}

	let result = 1_000_000 * 10;
	for (let i = 0; i < nodes[cur].length; i++) {
		if (nodes[cur][i] === 0 || visited[i]) continue;

		visited[i] = true;
		result = Math.min(
			result,
			dfs(i, cost + nodes[cur][i], visited, nodes, depth + 1, N, org)
		);
		visited[i] = false;
	}
	return result;
}

function solution() {
	let idx = 0;
	let N = +input[idx++];

	let nodes = Array(N)
		.fill()
		.map(() => input[idx++].split(' ').map(Number));

	let answer = 1_000_000 * 10;
	for (let i = 0; i < N; i++) {
		let visited = Array(N).fill(false);
		visited[i] = true;
		answer = Math.min(answer, dfs(i, 0, visited, nodes, 1, N, i));
	}
	return answer;
}

console.log(solution());
