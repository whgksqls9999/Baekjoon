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
const arr = Array(N + 1)
	.fill()
	.map(() => []);

let [from, to] = input[idx++].split(' ').map(Number);
while (from !== -1 && to !== -1) {
	arr[from].push(to);
	arr[to].push(from);

	[from, to] = input[idx++].split(' ').map(Number);
}

function BFS(num) {
	const queue = new Queue();
	const visited = Array(N + 1).fill(-1);

	visited[num] = 0;
	queue.push(num);

	let max = 0;
	while (!queue.isEmpty()) {
		const cur = queue.poll();

		for (let next of arr[cur]) {
			if (visited[next] !== -1) continue;

			visited[next] = visited[cur] + 1;
			max = Math.max(max, visited[next]);
			queue.push(next);
		}
	}
	return max;
}
function solution() {
	let answer = ['', ''];

	const result = [0];
	let min = N + 1;
	for (let i = 1; i <= N; i++) {
		const cur = BFS(i);
		min = Math.min(min, cur);
		result.push(cur);
	}

	let cnt = 0;
	for (let i = 1; i < result.length; i++) {
		if (result[i] === min) {
			cnt++;
			answer[1] += `${i} `;
		}
	}
	answer[0] = `${min} ${cnt}`;
	answer[1].trim();

	console.log(answer.join('\n'));
}

solution();
