const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;

let T = +input[idx++];

function solution() {
	let answer = [];

	while (T-- > 0) {
		const data = new Set();
		const sound = input[idx++].split(' ').map((it) => it.trim());
		sound.forEach((it) => {
			data.add(it);
		});

		let inputStr = input[idx++].trim();
		while (inputStr !== 'what does the fox say?') {
			let cur = inputStr.split(' goes ')[1].trim();

			data.delete(cur);
			inputStr = input[idx++].trim();
		}

		let result = [];
		for (let i of sound) {
			if (data.has(i)) {
				result.push(i);
			}
		}
		answer.push(result.join(' '));
	}
	console.log(answer.join('\n'));
}

solution();
