'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let word = input[idx++].trim();
	let bomb = input[idx].trim();

	const stack = [];

	[].forEach.call(word, (it) => {
		stack.push(it);

		if (
			stack.length >= bomb.length &&
			stack[stack.length - 1] === bomb[bomb.length - 1]
		) {
			let inStack = stack.slice(stack.length - bomb.length);

			if (inStack.join('') === bomb) {
				stack.splice(stack.length - bomb.length, bomb.length);
			}
		}
	});

	return stack.join('') || 'FRULA';
}

console.log(solution());
