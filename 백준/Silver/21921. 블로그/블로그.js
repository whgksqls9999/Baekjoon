const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, X] = input[idx++].split(' ').map(Number);
	let arr = input[idx++].split(' ').map(Number);

	let prefixSum = [];

	arr.forEach((it, idx) => {
		prefixSum[idx] = it + (prefixSum[idx - 1] ?? 0);
	});

	let [l, r] = [0, X - 1];

	let max = 0;
	let cnt = 0;
	for (; r < prefixSum.length; l++, r++) {
		max = Math.max(max, prefixSum[r] - (prefixSum[l - 1] ?? 0));
	}

	[l, r] = [0, X - 1];
	for (; r < prefixSum.length; l++, r++) {
		if (prefixSum[r] - (prefixSum[l - 1] ?? 0) === max) cnt++;
	}

	if (max === 0) {
		return 'SAD';
	}
	return max + '\n' + cnt;
}

console.log(solution());
