const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, M] = input[idx++].split(' ').map(Number);
	let arr = input.slice(idx).map((it) => it.trim());

	let l = 0;
	let r = N - 1;
	while (l < r) {
		let mid = Math.floor((l + r) / 2);

		let set = new Set();
		for (let i = 0; i < M; i++) {
			let cur = '';
			for (let j = mid + 1; j < N; j++) {
				cur += arr[j][i];
			}
			set.add(cur);
		}

		if (set.size < M) {
			r = mid;
		} else {
			l = mid + 1;
		}
	}

	return r;
}

console.log(solution());
