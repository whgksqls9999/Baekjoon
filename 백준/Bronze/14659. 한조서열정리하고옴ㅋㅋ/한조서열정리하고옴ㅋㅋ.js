const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);

	let max = 0;
	let cur = 0;
	for (let i of arr) {
		if (i > max) {
			max = i;
			answer = Math.max(cur, answer);
			cur = 0;
		} else if (i < max) {
			cur++;
		}
	}

	answer = Math.max(cur, answer);

	return answer;
}

console.log(solution());
