const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [M, N] = input[idx++].split(' ').map(Number);
const arr = input[idx++]
	.split(' ')
	.map(Number)
	.sort((a, b) => a - b);

function solution() {
	let l = 1;
	let r = arr[arr.length - 1] + 1;

	let answer = 0;
	while (l < r) {
		let mid = Math.floor((l + r) / 2);

		let cnt = 0;
		for (let i = 0; i < arr.length; i++) {
			if (mid > arr[i]) continue;

			cnt += Math.floor(arr[i] / mid);
		}

		if (cnt < M) {
			r = mid;
		} else {
			l = mid + 1;
		}
	}
	answer = r - 1;

	console.log(answer);
}

solution();
