'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let T = +input[idx++];

	let answer = [];
	for (let tc = 0; tc < T; tc++) {
		let N = +input[idx++];
		let arr = input[idx++]
			.split(' ')
			.map(Number)
			.sort((a, b) => a - b);

		let set = new Set(arr);

		let result = 0;
		for (let i = 0; i < arr.length - 2; i++) {
			for (let j = arr.length - 1; j >= i + 2; j--) {
				if (set.has((arr[i] + arr[j]) / 2)) result++;
			}
		}

		answer.push(result);
	}

	return answer.join('\n');
}

console.log(solution());
