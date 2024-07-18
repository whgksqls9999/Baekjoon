const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, K] = input[idx++].split(' ').map(Number);
	let arr = input.slice(idx).map(Number);

	let l = 0;
	let r = Math.max(...arr) + 1;

	while (l < r) {
		let mid = Math.floor((l + r) / 2);

		let cnt = 0;
		arr.forEach((it) => {
			cnt += isFinite(Math.floor(it / mid)) ? Math.floor(it / mid) : 1;
		});

		if (cnt >= K) {
			l = mid + 1;
		} else if (cnt < K) {
			r = mid;
		}
	}

	return r - 1;
}

console.log(solution());
