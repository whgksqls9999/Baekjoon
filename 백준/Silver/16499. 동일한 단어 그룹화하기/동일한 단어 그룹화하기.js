'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let set = new Set();

	for (let i = 0; i < N; i++) {
		let cur = input[idx++].trim();

		set.add([...cur].sort((a, b) => a.localeCompare(b)).join(''));
	}

	return set.size;
}

console.log(solution());
