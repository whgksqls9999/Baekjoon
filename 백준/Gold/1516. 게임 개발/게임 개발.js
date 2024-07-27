'use strict';

const input = require('fs')
	.readFileSync(0)
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

function solution() {
	let idx = 0;
	let N = +input[idx++];

	let times = Array(N).fill(0);
	let order = Array(N).fill(0);
	let dp = Array(N).fill(0);
	let nodes = Array(N)
		.fill()
		.map(() => []);

	for (let i = 0; i < N; i++) {
		let [time, ...args] = input[idx++].split(' ').map(Number);

		times[i] = time;
		dp[i] = time;

		if (args.length === 1) continue;

		for (let j of args.slice(0, args.length - 1)) {
			nodes[j - 1].push(i);
			order[i]++;
		}
	}

	let queue = new Queue();

	for (let i = 0; i < N; i++) {
		if (order[i] === 0) {
			queue.push(i);
		}
	}

	while (!queue.isEmpty()) {
		let cur = queue.poll();

		for (let i of nodes[cur]) {
			order[i]--;
			dp[i] = Math.max(dp[cur] + times[i], dp[i]);

			if (order[i] === 0) {
				queue.push(i);
			}
		}
	}

	return dp.join('\n');
}

console.log(solution());
