const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let T = +input[idx++];

function GCD(a, b) {
	let c;
	while (b !== 0) {
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

function LCM(a, b) {
	return (a * b) / GCD(a, b);
}
function solution() {
	let answer = [];

	while (T-- > 0) {
		const [N, M, x, y] = input[idx++].split(' ').map(Number);
		let limit = LCM(N, M);
		let result = x;
		while (result <= limit) {
			let l = result % N;
			if (l === 0) {
				l = N;
			}

			let r = result % M;
			if (r === 0) {
				r = M;
			}

			const cur = `${l} ${r}`;
			if (cur === `${x} ${y}`) {
				break;
			}

			result += N;
		}

		if (result <= limit) {
			answer.push(result);
		} else {
			answer.push(-1);
		}
	}

	console.log(answer.join('\n'));
}

solution();

// X % 10 === 3
// X % 12 === 9
