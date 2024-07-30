'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];
	let T = +input[idx++];

	for (let i = 0; i < T; i++) {
		let arr = [];

		for (let j = 0; j < 3; j++) {
			let N = +input[idx++];

			arr.push(input[idx++].split(' ').map(Number));
		}

		let set = new Set();

		for (let i = 0; i < arr[0].length; i++) {
			for (let j = 0; j < arr[1].length; j++) {
				outer: for (let k = 0; k < arr[2].length; k++) {
					let cur = arr[0][i] + arr[1][j] + arr[2][k];

					for (let char of cur.toString()) {
						if (char !== '5' && char !== '8') continue outer;
					}

					set.add(cur);
				}
			}
		}
		answer.push(set.size);
	}

	return answer.join('\n');
}

console.log(solution());
