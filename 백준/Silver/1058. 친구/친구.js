const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;

	const N = Number(input[idx++]);
	const answer = Array(N)
		.fill()
		.map(() => Array(N).fill(false));
	const arr = Array(N)
		.fill()
		.map(() => input[idx++].trim().split(''));

	for (let i = 0; i < N; i++) {
		for (let j = 0; j < N; j++) {
			for (let k = 0; k < N; k++) {
				if (j === k) continue;
				if (
					arr[j][k] === 'Y' ||
					(arr[j][i] === 'Y' && arr[i][k] === 'Y')
				) {
					answer[j][k] = true;
				}
			}
		}
	}
	let result = 0;
	for (let i of answer) {
		result = Math.max(
			result,
			i.reduce((prev, cur) => prev + cur, 0)
		);
	}
	return result;
}

console.log(solution());
