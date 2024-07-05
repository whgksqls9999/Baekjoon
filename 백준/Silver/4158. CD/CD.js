const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;

function solution() {
	let answer = [];

	let [N, M] = input[idx++].split(' ').map(Number);

	while (N !== 0 && M !== 0) {
		let result = 0;
		const set = new Set();
		for (let i = 0; i < N; i++) {
			set.add(+input[idx++]);
		}

		for (let i = 0; i < M; i++) {
			if (set.has(+input[idx++])) {
				result++;
			}
		}

		answer.push(result);
		[N, M] = input[idx++].split(' ').map(Number);
	}

	console.log(answer.join('\n'));
}

solution();
