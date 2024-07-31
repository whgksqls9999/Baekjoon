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
	let [N, M] = input[idx++].split(' ').map(Number);
	let str = input[idx++].split(' ').join('');

	let answer = -1;

	const queue = new Queue();
	const set = new Set();

	queue.push([str, 0]);
	set.add(str);

	while (!queue.isEmpty()) {
		let [curValue, curCnt] = queue.poll();

		let check = true;
		for (let i = 1; i < curValue.length; i++) {
			if (curValue[i - 1] > curValue[i]) {
				check = false;
				break;
			}
		}

		if (check) {
			return (answer = curCnt);
		}

		for (let i = 0; i <= str.length - M; i++) {
			let nextValue =
				curValue.slice(0, i) +
				[...curValue.slice(i, i + M)].reverse().join('') +
				curValue.slice(i + M);

			if (set.has(nextValue)) continue;

			set.add(nextValue);
			queue.push([nextValue, curCnt + 1]);
		}
	}

	return answer;
}

console.log(solution());
