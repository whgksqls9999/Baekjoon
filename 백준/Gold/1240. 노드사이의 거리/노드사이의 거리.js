const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	function dfs(n, visited, nodes, distance, destination) {
		if (n === destination) {
			return distance;
		}

		let result = 0;

		visited[n] = true;

		const curNodes = nodes[n];
		for (let [next, dist] of curNodes) {
			if (!visited[next]) {
				result = Math.max(
					dfs(next, visited, nodes, distance + dist, destination),
					result
				);
			}
		}

		return result;
	}
	const [N, M] = input[idx++].split(' ').map(Number);
	const nodes = Array(N + 1)
		.fill()
		.map(() => []);

	for (let i = 1; i < N; i++) {
		const [a, b, c] = input[idx++].split(' ').map(Number);

		nodes[a].push([b, c]);
		nodes[b].push([a, c]);
	}

	for (let i = 0; i < M; i++) {
		const [a, b] = input[idx++].split(' ').map(Number);
		const visited = Array(N + 1).fill(false);

		answer.push(dfs(a, visited, nodes, 0, b));
	}

	return answer.join('\n');
}

console.log(solution());
