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

	isEmpty() {
		return this.front === this.rear;
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
		return this.storage[this.front + 1];
	}
}

let idx = 0;
let K = +input[idx++];
const [M, N] = input[idx++].split(' ').map(Number);
const arr = Array(N)
	.fill()
	.map(() => input[idx++].split(' ').map(Number));

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
const hdr = [-2, -1, 1, 2, 2, 1, -1, -2];
const hdc = [1, 2, 2, 1, -1, -2, -2, -1];

function BFS(r, c) {
	const queue = new Queue();
	const visited = Array(K + 1)
		.fill()
		.map(() =>
			Array(N)
				.fill()
				.map(() => Array(M).fill(false))
		);

	queue.push([r, c, K, 0]);
	visited[K][r][c] = true;

	while (!queue.isEmpty()) {
		const [r, c, limit, dist] = queue.poll();

		if (r === N - 1 && c === M - 1) {
			return dist;
		}

		for (let i = 0; i < dr.length; i++) {
			let nr = r + dr[i];
			let nc = c + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (visited[limit][nr][nc] || arr[nr][nc] === 1) continue;

				queue.push([nr, nc, limit, dist + 1]);
				visited[limit][nr][nc] = true;
			}
		}

		if (limit === 0) continue;

		for (let i = 0; i < hdr.length; i++) {
			let nr = r + hdr[i];
			let nc = c + hdc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (visited[limit - 1][nr][nc] || arr[nr][nc] === 1) continue;

				queue.push([nr, nc, limit - 1, dist + 1]);
				visited[limit - 1][nr][nc] = true;
			}
		}
	}
}

function solution() {
	const answer = BFS(0, 0);

	console.log(answer ?? -1);
}

solution();
