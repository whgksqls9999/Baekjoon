const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const regex = /[a-zA-Z-]+/g;
	let max = '';

	input.forEach((it) => {
		const cur = it.trim().split(' ');

		cur.forEach((it) => {
			const cur = it.toLowerCase().match(regex)?.join('');
			if (cur?.length > max.length) {
				max = cur;
			}
		});
	});

	answer.push(max);

	return answer.join('\n');
}

console.log(solution());
