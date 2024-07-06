const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');
class Queue {
	constructor() {
		this.storage = {};
		this.front = -1;
		this.rear = -1;
	}

	push(value) {
		this.storage[++this.rear] = value;
	}

	poll() {
		if (this.isEmpty()) return null;
		return this.storage[++this.front];
	}

	peek() {
		if (this.isEmpty()) return null;
		return this.storage[this.front];
	}

	isEmpty() {
		return this.front === this.rear;
	}
}

let idx = 0;
let [N, M] = input[idx++].split(' ').map(Number);
let nodes = Array(N + 1)
	.fill(0)
	.map(() => []);
for (let i = 0; i < M; i++) {
	let [s, e] = input[idx++].split(' ').map(Number);

	nodes[s].push(e);
	nodes[e].push(s);
}

let maxDist = 0;
function BFS(n) {
	const queue = new Queue();
	const visited = Array(N + 1).fill(-1);

	queue.push({ point: n, dist: 0 });
	visited[n] = 0;

	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let next of nodes[cur.point]) {
			if (visited[next] !== -1) continue;

			visited[next] = cur.dist + 1;
			maxDist = Math.max(maxDist, cur.dist + 1);
			queue.push({ point: next, dist: cur.dist + 1 });
		}
	}

	return visited;
}

function solution() {
	const result = BFS(1);

	let cnt = 0;
	let longgest = 0;
	for (let i = 1; i < result.length; i++) {
		if (result[i] === maxDist) {
			cnt++;
			if (longgest === 0) {
				longgest = i;
			}
		}
	}

	console.log(`${longgest} ${maxDist} ${cnt}`);
}

solution();
