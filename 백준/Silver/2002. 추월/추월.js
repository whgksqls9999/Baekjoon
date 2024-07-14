const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx++];

function solution() {
	const carIn = new Set();
	const carOut = new Set();

	for (let j = 0; j < N; j++) {
		let cur = input[idx++].trim();

		carIn.add(cur);
	}

	let answer = 0;
	for (let j = 0; j < N; j++) {
		let cur = input[idx++].trim();

		carOut.add(cur);

		for (let key of carIn) {
			if (key === cur) break;

			if (!carOut.has(key)) {
				answer++;
				break;
			}
		}
	}
	console.log(answer);
}

solution();
