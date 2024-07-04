const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n')
	.map(Number);

const arr = Array(123457 * 2).fill(1);

function solution() {
	for (let i = 2; i <= Math.sqrt(arr.length); i++) {
		if (arr[i] === 1) {
			for (let j = i + i; j < arr.length; j += i) {
				arr[j] = 0;
			}
		}
	}
	arr[0] = 0;
	arr[1] = 0;

	const sum = Array(arr.length).fill(0);

	for (let i = 1; i < arr.length; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}

	let answer = '';

	for (let query of input) {
		if (query === 0) break;

		answer += `${sum[query * 2] - sum[query]}\n`;
	}

	console.log(answer.trim());
}

solution();
