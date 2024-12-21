const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const T = Number(input[idx++]);

	for (let tc = 0; tc < T; tc++) {
		const [N, M] = input[idx++].split(' ').map(Number);

		for (let i = 0; i < M; i++) {
			idx++;
		}

		answer.push(N - 1);
	}

	return answer.join('\n');
}

console.log(solution());
