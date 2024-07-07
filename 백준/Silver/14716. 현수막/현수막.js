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
const [N, M] = input[idx++].split(' ').map(Number);
const arr = Array(N)
	.fill(0)
	.map(() => Array(M).fill(0));

const visited = Array(N)
	.fill(0)
	.map(() => Array(M).fill(false));

let char = new Queue();
for (let i = 0; i < N; i++) {
	let cur = input[idx++].split(' ').map(Number);
	for (let j = 0; j < M; j++) {
		arr[i][j] = cur[j];
		if (cur[j] === 1) {
			char.push([i, j]);
		}
	}
}

const dr = [-1, -1, 0, 1, 1, 1, 0, -1];
const dc = [0, 1, 1, 1, 0, -1, -1, -1];

function BFS(r, c) {
	const queue = new Queue();

	visited[r][c] = true;
	queue.push([r, c]);

	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let i = 0; i < dr.length; i++) {
			let nr = dr[i] + cur[0];
			let nc = dc[i] + cur[1];

			if (
				nr >= 0 &&
				nr < N &&
				nc >= 0 &&
				nc <= M &&
				!visited[nr][nc] &&
				arr[nr][nc] === 1
			) {
				visited[nr][nc] = true;
				queue.push([nr, nc]);
			}
		}
	}
}

function solution() {
	let answer = 0;

	while (!char.isEmpty()) {
		let [r, c] = char.poll();

		if (!visited[r][c]) {
			BFS(r, c);
			answer++;
		}
	}

	console.log(answer);
}

solution();
