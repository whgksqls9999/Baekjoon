const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, W, H] = input[idx++].split(' ').map(Number);
	let arr = input.slice(idx++).map(Number);

	let limit = Math.sqrt(W ** 2 + H ** 2);

	let answer = [];

	arr.forEach((it) => {
		if (it <= limit) {
			answer.push('DA');
			return;
		}

		answer.push('NE');
	});

	return answer.join('\n');
}

console.log(solution());
