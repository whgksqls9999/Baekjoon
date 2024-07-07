const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx++];
const word = 'IOI';
idx++;
const str = input[idx++].trim();

function solution() {
	let answer = 0;

	let check = 1;
	let pos = 0;
	let result = [];
	while (check !== -1) {
		check = str.indexOf(word, pos);
		result.push(check);
		pos = check + 2;
	}
	pos = 0;
	let result2 = [1];
	for (let i = 1; i < result.length - 1; i++) {
		if (result[i] - result[i - 1] === 2) {
			result2[pos]++;
		} else {
			result2[++pos] = 1;
		}
	}

	for (let key of result2) {
		let cur = key - N + 1;
		answer += cur > 0 ? cur : 0;
	}
	console.log(answer);
}

solution();
