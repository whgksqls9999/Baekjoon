const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const N = input[idx++].trim();
	const arr = [];
	for (let i of N) {
		let cur = Number(i);

		if (cur === 0) {
			arr[arr.length - 1] *= 10;
		} else {
			arr.push(cur);
		}
	}
	answer = arr.reduce((prev, cur) => prev + cur, 0);

	return answer;
}

console.log(solution());
