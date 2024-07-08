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
const N = +input[idx++];
const arr = Array(N)
	.fill()
	.map(() => input[idx++].split(' ').map(Number));
const visited = Array(N)
	.fill()
	.map(() => Array(N).fill(-1));

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

function BFS(r, c, num) {
	const queue = new Queue();

	queue.push([r, c]);
	visited[r][c] = num;

	const tmp = [];
	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let i = 0; i < dr.length; i++) {
			let nr = cur[0] + dr[i];
			let nc = cur[1] + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (visited[nr][nc] === -1) {
					if (arr[nr][nc] === 0) {
						tmp.push(cur);
						continue;
					}
					visited[nr][nc] = num;
					queue.push([nr, nc]);
				}
			}
		}
	}
	return tmp;
}

function findShortest(_arr, num) {
	const queue = new Queue();
	const _visited = Array(N)
		.fill()
		.map(() => Array(N).fill(-1));

	_arr.forEach((it) => {
		queue.push(it);
		_visited[it[0]][it[1]] = 0;
	});

	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let i = 0; i < dr.length; i++) {
			let nr = cur[0] + dr[i];
			let nc = cur[1] + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (_visited[nr][nc] === -1 && visited[nr][nc] !== num) {
					if (visited[nr][nc] !== -1) {
						return _visited[cur[0]][cur[1]];
					}
					_visited[nr][nc] = _visited[cur[0]][cur[1]] + 1;
					queue.push([nr, nc]);
				}
			}
		}
	}
}

function solution() {
	let answer = 201;

	let num = 1;
	let result = [];
	for (let i = 0; i < N; i++) {
		for (let j = 0; j < N; j++) {
			if (visited[i][j] === -1 && arr[i][j] !== 0) {
				result[num] = BFS(i, j, num++);
			}
		}
	}

	for (let i = 1; i < result.length; i++) {
		answer = Math.min(findShortest(result[i], i), answer);
	}

	console.log(answer);
}

solution();
