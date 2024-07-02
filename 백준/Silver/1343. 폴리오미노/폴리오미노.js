const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const str = input[0].trim();

const A = 'AAAA';
const B = 'BB';
const action = {
	2: function (word) {
		return word + B;
	},
	4: function (word) {
		return word + A;
	},
};

function solution() {
	let x = 0;
	let answer = '';

	for (let i = 0; i < str.length; i++) {
		if (answer === undefined) break;

		if (x === 4) {
			answer = action[x](answer);
			x = 0;
		}

		if (str[i] === '.') {
			if (x > 0) {
				answer = action[x]?.(answer);
				x = 0;
			}
			if (answer !== undefined) answer += '.';
		} else {
			x++;
		}
	}

	if (x) {
		answer = action[x]?.(answer);
	}

	console.log(answer ?? -1);
}

solution();
