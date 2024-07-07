const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const arr = [];
for (let i = 0; i < 3; i++) {
	let cur = isNaN(+input[idx]) ? input[idx++].trim() : +input[idx++];

	if (typeof arr[i - 1] === 'number') {
		cur = arr[i - 1] + 1;
	}

	arr[i] = cur;
}

function solution() {
	let answer = [];

	let cur = arr[3] ?? arr[2] + 1;
	if (cur % 3 === 0) {
		answer.push('Fizz');
	}
	if (cur % 5 === 0) {
		answer.push('Buzz');
	}
	if (answer.length === 0) {
		answer.push(cur);
	}

	console.log(answer.join(''));
}

solution();
