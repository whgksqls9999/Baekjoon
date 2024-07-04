const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const L = +input[0].split(' ')[1];
const arr = input[1]
	.split(' ')
	.map((it) => +it)
	.sort((a, b) => a - b);

function solution() {
	let answer = 1;
	let s = arr[0] - 0.5;
	let e = s + L;

	for (let key of arr) {
		if (key > e) {
			s = key - 0.5;
			e = s + L;
			answer++;
		}
	}

	console.log(answer);
}

solution();
