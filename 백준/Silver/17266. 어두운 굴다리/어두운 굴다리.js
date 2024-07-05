const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let N = +input[idx++];
let M = +input[idx++];
let pos = input[idx++].split(' ').map(Number);

function binarySearch(l, r) {
	if (l === r) {
		return l;
	}

	let mid = Math.floor((l + r) / 2);
	let max = 0;
	let arr = Array(N + 1);
	let needLarger = false;
	pos.forEach((it) => {
		let i = Math.max(0, it - mid);
		if (i > max) {
			needLarger = true;
			return;
		}
		max = Math.min(it + mid, N + 1);
	});

	if (max < N) {
		needLarger = true;
	}

	let res = Number.MAX_SAFE_INTEGER;
	if (needLarger) {
		res = Math.min(res, binarySearch(mid + 1, r));
	} else {
		res = Math.min(res, binarySearch(l, mid));
	}

	return res;
}

function solution() {
	console.log(binarySearch(1, N));
}

solution();
