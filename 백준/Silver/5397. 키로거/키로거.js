const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let T = +input[idx++];
	let answer = [];

	for (let i = 0; i < T; i++) {
		let stack = [];
		let left = [];

		let str = input[idx++].trim();

		for (let j = 0; j < str.length; j++) {
			let cur = str[j];

			if (cur === '<') {
				if (stack.length === 0) continue;
				left.push(stack.pop());
			} else if (cur === '>') {
				if (left.length === 0) continue;
				stack.push(left.pop());
			} else if (cur === '-') {
				if (stack.length === 0) continue;
				stack.pop();
			} else {
				stack.push(cur);
			}
		}

		while (left.length !== 0) {
			stack.push(left.pop());
		}

		answer.push(stack.join(''));
	}
	return answer.join('\n');
}

console.log(solution());
