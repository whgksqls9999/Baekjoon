const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx++];
const word = 'I' + 'OI'.repeat(N);
idx++;
const str = input[idx++].trim();

function solution() {
	let answer = 0;

	let l = 0;
	let r = word.length;
	while (r <= str.length) {
		let cur = str.slice(l++, r++);

		if (cur === word) {
			answer++;
		}
	}

	console.log(answer);
}

solution();
