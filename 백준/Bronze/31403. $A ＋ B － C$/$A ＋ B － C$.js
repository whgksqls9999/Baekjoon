const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let arr = [];
for (let i = 0; i < 3; i++) {
	arr.push(+input[idx++]);
}

function solution() {
	let answer = [];

	answer.push(arr[0] + arr[1] - arr[2]);
	answer.push(arr[0].toString() + arr[1] - arr[2]);

	console.log(answer.join('\n'));
}

solution();
