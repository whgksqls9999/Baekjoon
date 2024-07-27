'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function calculate(num) {
	let min = 0;
	let max = 0;

	for (let i of num.toString()) {
		if (i === '5' || i === '6') {
			min += '5';
			max += '6';
		} else {
			min += i;
			max += i;
		}
	}

	return {
		min: +min,
		max: +max,
	};
}

function solution() {
	let idx = 0;
	let [N, M] = input[idx].split(' ').map((it) => calculate(+it));

	return `${N.min + M.min} ${N.max + M.max}`;
}

console.log(solution());
