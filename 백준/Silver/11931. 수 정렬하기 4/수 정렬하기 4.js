const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const [N, ...arr] = input.map(Number);
const gap = 1_000_000;
const count = Array(2_000_001).fill(0);

function solution() {
	let answer = [];

	for (let value of arr) {
		count[value + gap]++;
	}

	for (let i = count.length - 1; i >= 0; i--) {
		if (count[i] !== 0) {
			for (let j = 0; j < count[i]; j++) {
				answer.push(i - gap);
			}
		}
	}

	console.log(answer.join(' '));
}

solution();
