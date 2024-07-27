'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, T] = input[idx++].split(' ').map(Number);
	let answer = Number.MAX_SAFE_INTEGER;

	for (let i = 0; i < N; i++) {
		let [s, d, n] = input[idx++].split(' ').map(Number);

		if (s + d * (n - 1) < T) continue;

		let l = 0;
		let r = n;

		while (l < r) {
			let mid = Math.floor((l + r) / 2);

			let curTime = s + mid * d;
			if (curTime >= T) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}

		answer = Math.min(answer, s + d * r - T);
	}

	return answer !== Number.MAX_SAFE_INTEGER ? answer : -1;
}

console.log(solution());
