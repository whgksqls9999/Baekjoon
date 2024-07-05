const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [N, M] = input[idx++].split(' ').map(Number);

function solution() {
	let answer = [];

	const set = new Set();
	for (let i = 0; i < N; i++) {
		set.add(input[idx++].trim());
	}

	for (let i = 0; i < M; i++) {
		const keywords = input[idx++].trim().split(',');

		keywords.forEach((it) => {
			if (set.has(it)) {
				set.delete(it);
			}
		});
		answer.push(set.size);
	}

	console.log(answer.join('\n'));
}

solution();
