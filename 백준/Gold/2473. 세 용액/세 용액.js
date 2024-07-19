const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let a = 0;
	let N = +input[a++];
	let arr = input[a]
		.split(' ')
		.map(Number)
		.sort((a, b) => a - b);

	let max = Number.MAX_SAFE_INTEGER;
	let answer = '';

	arr.forEach((it, idx) => {
		let l = 0;
		let r = arr.length - 1;

		while (l < r) {
			if (idx === l) {
				l++;
			}
			if (idx === r) {
				r--;
			}
			if (r <= l) break;

			let res = it + arr[l] + arr[r];
			if (max > Math.abs(res)) {
				max = Math.abs(res);
				answer = [it, arr[l], arr[r]].sort((a, b) => a - b);
			}

			if (res < 0) {
				l++;
			} else if (res > 0) {
				r--;
			} else {
				break;
			}
		}

		// console.log(idx, l, r, arr, max);
	});

	return answer.join(' ');
}

console.log(solution());
