const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let N = +input[idx++];
const arr = input[idx].split(' ').map(Number);
const check = Array(1000001);

function solution() {
	const map = {};

	for (let i of arr) {
		check[i] = 0;
	}

	for (let i of arr) {
		for (let j = i * 2; j <= 1000001; j += i) {
			if (check[j] === undefined) continue;
			check[i]++;
			check[j]--;
		}
	}

	const answer = [];
	for (let i of arr) {
		answer.push(check[i]);
	}
	console.log(answer.join(' '));
}

solution();
