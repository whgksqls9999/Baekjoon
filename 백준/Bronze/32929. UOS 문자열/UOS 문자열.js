const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const N = Number(input[idx++]);

	switch (N % 3) {
		case 0:
			answer.push('S');
			break;
		case 1:
			answer.push('U');
			break;
		case 2:
			answer.push('O');
			break;
	}
	return answer.join('');
}

console.log(solution());
