const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let N = Number(input[0]);

function solution() {
	let answer = 0;

	for (let i = 8; i >= 0; i--) {
		let cur = Math.pow(10, i);

		if (N >= cur) {
			answer += (N - (cur - 1)) * (i + 1);
			N = Math.pow(10, i) - 1;
		}
	}

	console.log(answer);
}

solution();
