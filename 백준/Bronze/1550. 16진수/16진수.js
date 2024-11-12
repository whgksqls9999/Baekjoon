const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;
	const N = input[idx++].trim();

	function trans(arg) {
		const trans = {
			A: 10,
			B: 11,
			C: 12,
			D: 13,
			E: 14,
			F: 15,
		};

		return trans[arg];
	}

	for (let i = 0; i < N.length; i++) {
		let cur = trans([N[i]]) ?? N[i];
		answer += Math.pow(16, N.length - i - 1) * cur;
	}

	return answer;
}

console.log(solution());
