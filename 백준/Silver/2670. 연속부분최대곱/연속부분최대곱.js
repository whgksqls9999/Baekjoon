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

function solution() {
	let idx = 0;

	const answer = [];
	let res = 0;

	const N = Number(input[idx++]);
	const arr = Array(N)
		.fill()
		.map(() => Number(input[idx++]));
	const dp = Array(N).fill(0);
	dp[0] = arr[0];

	for (let i = 1; i < N; i++) {
		dp[i] = Math.max(dp[i - 1] * arr[i], arr[i]);
		res = Math.max(res, dp[i]);
	}

	answer.push(res.toFixed(3));

	return answer.join('\n');
}

console.log(solution());
