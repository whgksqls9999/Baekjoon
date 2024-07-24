const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let answer = [];

	for (let i = 0; i < N; i++) {
		answer.push(`Scenario #${i + 1}:`);
		let cur = input[idx++]
			.split(' ')
			.map(Number)
			.sort((a, b) => a - b);
		// console.log(Math.floor(cur[0] ** 2 + cur[1] ** 2, cur[2] ** 2);
		answer.push(
			cur[0] ** 2 + cur[1] ** 2 === cur[2] ** 2 ? 'yes' : 'no',
			''
		);
	}
	return answer.join('\n').trim();
}

console.log(solution());
