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

	let l = 1;
	let r = Math.max(...arr);
	let answer = r;

	while (l < r) {
		let mid = l + Math.floor((r - l) / 2);

		let cnt = 0;
		let min = 0;

		arr.forEach((it) => {
			let times = Math.max(Math.ceil(it / mid), 1);
			cnt += times;
			min = Math.max(min, mid);
		});

		if (cnt > N) {
			l = mid + 1;
		} else {
			r = mid;
			answer = Math.min(answer, min);
		}
	}

	return answer;
}

console.log(solution());
