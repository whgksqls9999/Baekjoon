const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input[idx]
		.split(' ')
		.map(Number)
		.sort((a, b) => a - b);

	let result = 0;
	for (let i = 0; i < arr.length; i++) {
		let cur = arr[i];

		let l = 0;
		let r = arr.length - 1;
		while (true) {
			if (r === i) r--;
			if (l === i) l++;

			if (l >= r) break;

			let sum = arr[l] + arr[r];

			if (sum > cur) {
				r--;
			} else if (sum < cur) {
				l++;
			} else {
				result++;
				break;
			}
		}
	}

	return result;
}

console.log(solution());
