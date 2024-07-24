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
		let arr = input[idx++]
			.split(' ')
			.map(Number)
			.sort((a, b) => a - b);
		let set = new Set(arr);

		let CASE = `Case #${i + 1}: `;
		if (set.size === 1) {
			answer.push(CASE + 'equilateral');
		} else if (set.size === 2) {
			if (arr[2] < arr[0] + arr[1]) {
				answer.push(CASE + 'isosceles');
			} else {
				answer.push(CASE + 'invalid!');
			}
		} else {
			if (arr[2] < arr[0] + arr[1]) {
				answer.push(CASE + 'scalene');
			} else {
				answer.push(CASE + 'invalid!');
			}
		}
	}

	return answer.join('\n').trim();
}

console.log(solution());
