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
	.fill()
	.map((it) => input[idx++].split(' ').map(Number));
const visited = Array(N)
	.fill()
	.map(() => Array(M).fill(false));
const D = [
	[-1, 0],
	[-1, 1],
	[0, 1],
	[1, 1],
	[1, 0],
	[1, -1],
	[0, -1],
	[-1, -1],
];

function BFS(r, c) {
	const queue = new Queue();

	visited[r][c] = 's';
	queue.push([r, c]);

	let check = false;

	let cnt = 0;
	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let d of D) {
			let nr = cur[0] + d[0];
			let nc = cur[1] + d[1];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (arr[nr][nc] > arr[cur[0]][cur[1]]) {
					check = true;
				}

				if (visited[nr][nc] !== false) continue;

				if (arr[nr][nc] === arr[cur[0]][cur[1]]) {
					queue.push([nr, nc]);
					visited[nr][nc] = arr[nr][nc].toString();
				}
			}
		}
	}

	if (check) {
		return 0;
	}
	return 1;
}

function solution() {
	let answer = 0;

	for (let i = 0; i < N; i++) {
		for (let j = 0; j < M; j++) {
			if (!visited[i][j]) {
				answer += BFS(i, j);
			}
		}
	}

	console.log(answer);
}

solution();
