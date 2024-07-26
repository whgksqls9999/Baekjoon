'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input[idx++].split(' ').map(Number);
	let dist = input[idx++]?.split(' ').map(Number);

	let answer = '권병장님, 중대장님이 찾으십니다';
	if (N === 1) {
		return answer;
	}

	let max = 0;

	for (let i = 0; i < N; i++) {
		let it = arr[i];

		if (it > max) {
			answer = '엄마 나 전역 늦어질 것 같아';
			return answer;
		}

		max = Math.max(max, it + dist[i]);
	}

	return answer;
}

console.log(solution());
