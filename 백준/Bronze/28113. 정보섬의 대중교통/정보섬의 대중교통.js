'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, A, B] = input[idx].split(' ').map(Number);

	let answer;
	if (N > B) {
		answer = 'Bus';
	} else {
		if (A === B) {
			answer = 'Anything';
		} else if (A > B) {
			answer = 'Subway';
		} else {
			answer = 'Bus';
		}
	}
	return answer;
}

console.log(solution());
