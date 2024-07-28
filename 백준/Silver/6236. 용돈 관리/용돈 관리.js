'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, M] = input[idx++].split(' ').map(Number);
	let arr = input.slice(idx).map(Number);

	let l = Math.max(...arr);
	let r = 10000 * 100_000;

	while (l < r) {
		let mid = Math.floor((l + r) / 2);
		let rest = mid;

		let cnt = 1;
		arr.forEach((it) => {
			rest -= it;

			if (rest < 0) {
				rest = mid - it;
				cnt++;
			}
		});

		if (cnt > M) {
			l = mid + 1;
		} else {
			r = mid;
		}
	}

	return r;
}

console.log(solution());
