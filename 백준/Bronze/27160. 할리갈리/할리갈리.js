'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let map = new Map();

	for (let i = 0; i < N; i++) {
		let [name, cnt] = input[idx++].trim().split(' ');

		map.set(name, (isNaN(map.get(name)) ? 0 : map.get(name)) + +cnt);
	}

	return [...map.values()].some((it) => it === 5) ? 'YES' : 'NO';
}

console.log(solution());
