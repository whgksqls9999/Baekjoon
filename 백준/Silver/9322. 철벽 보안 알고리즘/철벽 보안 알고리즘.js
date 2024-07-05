const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let T = +input[idx++];

function solution() {
	let answer = [];

	while (T-- > 0) {
		let N = +input[idx++];
		const first = input[idx++].trim().split(' ');
		const second = input[idx++].trim().split(' ');
		const ordinary = input[idx++].trim().split(' ');

		const map = new Map();
		first.forEach((it, idx) => {
			map.set(it, idx);
		});

		const order = Array(N);
		second.forEach((it, idx) => {
			order[map.get(it)] = idx;
		});

		const result = Array(N);
		order.forEach((it, idx) => {
			result[idx] = ordinary[it];
		});

		answer.push(result.join(' '));
	}

	console.log(answer.join('\n'));
}

solution();
