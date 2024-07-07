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

	push(value) {
		this.storage[++this.rear] = value;
	}

	poll() {
		if (this.isEmpty()) return null;
		return this.storage[++this.front];
	}

	peek() {
		if (this.isEmpty()) return null;
		return this.storage[this.front];
	}

	isEmpty() {
		return this.front === this.rear;
	}
}

let idx = 0;
let N = +input[idx++];
const arr = input[idx++].split(' ').map(Number);
let [T, P] = input[idx++].split(' ').map(Number);

function solution() {
	let answer = [];

	let result = 0;
	for (let i = 0; i < arr.length; i++) {
		result += Math.ceil(arr[i] / T);
	}

	answer.push(result);
	answer.push(`${Math.floor(N / P)} ${N % P}`);

	console.log(answer.join('\n'));
}

solution();
