const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	for (const words of input) {
		const [A, B] = words.trim().split(' ');

		let i = 0;
		for (let char of B) {
			if (char === A[i]) {
				i++;
			}
		}

		if (i === A.length) {
			answer.push('Yes');
		} else {
			answer.push('No');
		}
	}

	return answer.join('\n');
}

console.log(solution());
