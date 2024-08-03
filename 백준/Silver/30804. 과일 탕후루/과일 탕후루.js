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

	let cnt = Array(10).fill(0);

	let l = 0;
	let r = 1;
	cnt[arr[l]]++;
	cnt[arr[r]]++;

	let kind = arr[l] === arr[r] ? 1 : Math.min(N, 2);
	let max = r - l;

	while (l < r && r < arr.length) {
		if (kind > 2) {
			cnt[arr[l]]--;

			if (cnt[arr[l]] === 0) {
				kind--;
			}

			l++;
		} else {
			r++;

			cnt[arr[r]]++;

			if (cnt[arr[r]] === 1) {
				kind++;
			}
		}
		max = Math.max(max, r - l);
	}

	return max;
}

console.log(solution());
