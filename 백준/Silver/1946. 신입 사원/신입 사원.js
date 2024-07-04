const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let I = 0;
let T = +input[I++];

function solution() {
	let answer = '';

	while (T-- > 0) {
		let N = +input[I++];
		const arr = [];
		let a = (b = Number.MAX_VALUE);

		for (let i = 0; i < N; i++) {
			let cur = input[I++].split(' ').map(Number);
			arr.push(cur);

			a = Math.min(a, cur[0]);
			b = Math.min(b, cur[1]);
		}
		arr.sort((a, b) => a[0] - b[0]);
		let resultA = 0;
		let min = N + 1;
		for (let i = 0; i < arr.length; i++) {
			if (arr[i][1] < min) {
				resultA++;
			}
			min = Math.min(min, arr[i][1]);
		}

		// arr.sort((a, b) => a[1] - b[1]);

		// let resultB = 1;
		// min = arr[0][0];
		// for (let i = 1; i < arr.length; i++) {
		// 	if (arr[i][0] < min) {
		// 		resultB++;
		// 	}
		// 	min = Math.min(min, arr[i][1]);
		// }

		// answer += Math.max(resultA, resultB) + '\n';
		answer += resultA + '\n';
	}
	console.log(answer.trim());
}

solution();
