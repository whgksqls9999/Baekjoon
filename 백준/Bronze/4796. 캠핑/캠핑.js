'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	while (true) {
		let [L, P, V] = input[idx++].split(' ').map(Number);

		if (L === 0 && P === 0 && V === 0) {
			return answer.join('\n');
		}

		let times = Math.floor((V - L) / P);
		let rest = Math.max(V - P * (times + 1), 0);

		answer.push(`Case ${idx}: ${(times + 1) * L + rest}`);
	}
}

console.log(solution());
