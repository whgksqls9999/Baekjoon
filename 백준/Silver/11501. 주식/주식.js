const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	const T = Number(input[idx++]);
	for (let i = 0; i < T; i++) {
		let N = Number(input[idx++]);
		const arr = input[idx++].split(' ').map(Number);

		let result = 0;
		let max = 0;
		for (let j = arr.length - 1; j >= 0; j--) {
			if (arr[j] > max) {
				max = arr[j];
				continue;
			} else if (arr[j] < max) {
				result += max - arr[j];
			}
		}
		answer.push(result);
	}

	return answer.join('\n');
}

console.log(solution());
