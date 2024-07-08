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

const action = {
	D(n) {
		return (n * 2) % 10000;
	},
	S(n) {
		return n - 1 < 0 ? 9999 : n - 1;
	},
	L(n) {
		let str = n.toString().padStart(4, 0);
		return +str.slice(1).concat(str[0]);
	},
	R(n) {
		let str = n.toString().padStart(4, 0);
		return +str[3].concat(str.slice(0, 3));
	},
};
function BFS(n, m) {
	const queue = new Queue();
	const visited = Array(10000).fill(false);

	visited[n] = true;
	queue.push([n, '']);

	while (!queue.isEmpty()) {
		const [num, depth] = queue.poll();

		for (let act in action) {
			const next = action[act](num);
			if (visited[next]) continue;

			if (next === m) return `${depth}${act}`;

			queue.push([next, `${depth}${act}`]);
			visited[next] = true;
		}
	}
}

function solution() {
	let answer = [];

	for (let i = 0; i < N; i++) {
		const [N, M] = input[idx++].split(' ').map(Number);

		answer.push(BFS(N, M));
	}
	console.log(answer.join('\n'));
}

solution();
