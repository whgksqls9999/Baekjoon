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
	let order = Array(N + 1).fill(0);
	let nodes = Array(N + 1)
		.fill()
		.map(() => []);
	let times = Array(N + 1).fill(0);
	let consumedTimes = Array(N + 1).fill(0);

	for (let i = 0; i < N; i++) {
		let [time, M, ...from] = input[idx++].split(' ').map(Number);

		times[i + 1] = time;

		for (let j of from) {
			order[i + 1]++;
			nodes[j].push(i + 1);
		}
	}

	let queue = new Queue();

	for (let i = 1; i < order.length; i++) {
		if (order[i] === 0) {
			queue.push(i);
			consumedTimes[i] = times[i];
		}
	}

	while (!queue.isEmpty()) {
		let cur = queue.poll();

		for (let next of nodes[cur]) {
			order[next]--;
			consumedTimes[next] = Math.max(
				consumedTimes[next],
				consumedTimes[cur] + times[next]
			);

			if (order[next] === 0) {
				queue.push(next);
			}
		}
	}

	return Math.max(...consumedTimes);
}

console.log(solution());
