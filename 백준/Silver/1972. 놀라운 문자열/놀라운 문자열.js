const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;

function solution() {
	let answer = [];

	let str = input[idx++].trim();
	while (str !== '*') {
		let NOT = '';
		outer: for (let i = 1; i < str.length; i++) {
			const set = new Set();

			for (let j = 0; j + i <= str.length - 1; j++) {
				const cur = str[j] + str[j + i];

				if (set.has(cur)) {
					NOT = 'NOT ';
					break outer;
				}
				set.add(cur);
			}
		}
		answer.push(`${str} is ${NOT}surprising.`);
		str = input[idx++].trim();
	}

	console.log(answer.join('\n'));
}

solution();
