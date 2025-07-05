const file = process.platform === 'linux' ? '/dev/stdin' : 'example.txt';
const input = require('fs').readFileSync(file).toString().trim().split('\n');

function solution() {
	let idx = 0;

	const [N, W] = input[idx++].split(' ').map(Number);

	const nodes = Array(N + 1)
		.fill()
		.map(() => []);

	for (let i = 0; i < N - 1; i++) {
		const [s, e] = input[idx++].split(' ').map(Number);

		nodes[s].push(e);
		nodes[e].push(s);
	}

	const leafNodeCount = getLeafNodeCount(nodes);

	return (W / leafNodeCount).toFixed(10);
}

function getLeafNodeCount(nodes) {
	const visited = Array(nodes.length).fill(false);
	let isFromLeaf = false;
	let leafNodeCount = 0;

	dfs(1);

	// const stack = [];
	// stack.push(1);

	// while (true) {
	// 	let cur = stack.pop();
	// 	visited[cur] = true;
	// }

	function dfs(nodeIndex) {
		visited[nodeIndex] = true;

		for (const next of nodes[nodeIndex]) {
			if (!visited[next]) {
				isFromLeaf = false;
				dfs(next);
			}
		}

		if (!isFromLeaf) {
			isFromLeaf = true;
			leafNodeCount += 1;
		}
	}

	return leafNodeCount;
}

console.log(solution());
