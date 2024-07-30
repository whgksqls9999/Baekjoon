'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let students = input[idx++]
		.trim()
		.split(' ')
		.map((it) => [it, 0]);

	const map = new Map(students);

	for (let i = 0; i < N; i++) {
		let [...cur] = input[idx++].trim().split(' ');

		for (let j of cur) {
			map.set(j, (map.get(j) ?? 0) + 1);
		}
	}

	let rank = Array.from(map).sort((a, b) => {
		if (a[1] !== b[1]) {
			return b[1] - a[1];
		} else {
			return a[0].localeCompare(b[0]);
		}
	});

	let answer = [];
	for (let cur of rank) {
		answer.push(cur.join(' '));
	}

	return answer.join('\n');
}

console.log(solution());
