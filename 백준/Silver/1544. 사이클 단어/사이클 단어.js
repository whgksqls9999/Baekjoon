const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let N = +input[idx++];

function solution() {
	let answer = [];

	const set = new Set();
	while (N-- > 0) {
		const word = input[idx++].trim();

		let check = false;
		for (let i = 0; i < word.length; i++) {
			const cur = word.slice(i) + word.slice(0, i);

			if (set.has(cur)) {
				check = true;
				break;
			}
		}

		if (!check) {
			set.add(word);
		}
	}
	answer.push(set.size);

	console.log(answer.join('\n'));
}

solution();
