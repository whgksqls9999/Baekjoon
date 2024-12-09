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
	arr.sort((a, b) => b - a);

	for (let i = 1; i < arr.length; i++) {
		answer += arr[i];
	}

	return answer;
}

console.log(solution());
