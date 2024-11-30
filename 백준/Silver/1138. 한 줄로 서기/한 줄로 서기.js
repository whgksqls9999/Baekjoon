const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	const order = Array(N).fill(-1);

	outer: for (let i = 1; i <= N; i++) {
		let check = 0;
		let limit = arr[i - 1];

		for (let j = 0; j < N; j++) {
			if (check >= limit && order[j] === -1) {
				order[j] = i;
				continue outer;
			}

			if (order[j] === -1 || order[j] > i) {
				check++;
			}
		}
	}

	return order.join(' ');
}

console.log(solution());
