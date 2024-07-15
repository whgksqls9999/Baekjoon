const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const N = +input[idx++];
const arr = input[idx].split(' ').map((it) => it.trim());

function solution() {
	const set = new Set();

	for (let i of arr) {
		if (!i.endsWith('Cheese')) continue;
		set.add(i);

		if (set.size >= 4) {
			return 'yummy';
		}
	}
	return 'sad';
}

let answer = solution();
console.log(answer);
