const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function Prime() {
	const tmp = Array(1000001)
		.fill(0)
		.map((it, idx) => idx);

	for (let i = 2; i <= Math.sqrt(tmp.length); i++) {
		if (tmp[i] === i) {
			for (let j = i + i; j < tmp.length; j += i) {
				tmp[j] = 0;
			}
		}
	}

	return tmp;
}
const prime = new Prime();

let idx = 0;
function solution() {
	let answer = [];

	let cur = +input[idx++];
	while (cur !== 0) {
		let result = "Goldbach's conjecture is wrong.";

		for (let i = 3; i <= Math.ceil(cur / 2); i += 2) {
			if (prime[i] && prime[cur - i]) {
				result = `${cur} = ${i} + ${cur - i}`;
				break;
			}
		}

		answer.push(result);

		cur = +input[idx++];
	}

	console.log(answer.join('\n'));
}

solution();
