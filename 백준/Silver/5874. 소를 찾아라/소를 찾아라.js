const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let str = input[idx].trim();

	let front = 0;
	let rear = 0;

	let answer = 0;
	for (let i = 1; i < str.length; i++) {
		if (str[i] === '(' && str[i - 1] === '(') {
			front++;
		} else if (str[i] === ')' && str[i - 1] === ')') {
			answer += front;
		}
	}

	return answer;
}

console.log(solution());
