const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];
	const [N, M] = input[idx++].split(' ').map(Number);
	const arr = input.slice(idx).map((it) => it.trim());
	const A = 'A'.codePointAt(0);
	let diff = 0;
	let res = '';

	for (let i = 0; i < M; i++) {
		const count = Array('Z'.codePointAt(0) - A + 1).fill(0);

		let max = 0;
		for (let j = 0; j < N; j++) {
			max = Math.max(++count[arr[j][i].codePointAt(0) - A], max);
		}
		diff += N - max;

		for (let j = 0; j < count.length; j++) {
			if (count[j] === max) {
				res += String.fromCodePoint(j + A);
				break;
			}
		}
	}

	answer.push(res, diff);

	return answer.join('\n');
}

console.log(solution());
