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

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	const dp = Array(N).fill(0);

	let min = arr[0];
	let res = 0;
	for (let i = 0; i < N; i++) {
		if (arr[i] > min) {
			res = Math.max(arr[i] - min, res);
		} else if (arr[i] < min) {
			min = arr[i];
		}
	}
	answer.push(res);

	return answer.join('\n');
}

console.log(solution());
