const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const T = Number(input[idx++]);

	const answer = [];

	for (let tc = 0; tc < T; tc++) {
		let N = Number(input[idx++]);

		const arr = input[idx++].split(' ').map(Number);

		let result = arr[0];
		for (let i = 1; i < N; i++) {
			arr[i] += arr[i - 1];
		}

		for (let i = 0; i < N; i++) {
			for (let j = 0; j <= i; j++) {
				result = Math.max(result, arr[i] - (arr[j - 1] ?? 0));
			}
		}

		answer.push(result);
	}

	return answer.join('\n');
}

console.log(solution());
