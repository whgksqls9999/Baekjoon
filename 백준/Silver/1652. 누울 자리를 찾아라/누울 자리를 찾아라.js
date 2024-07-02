const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const arr = input.slice(1).map((it) => it.trim());

function checkHorizontal(arr) {
	let result = 0;

	for (let i of arr) {
		const split = i.split('X');

		for (let j of split) {
			if (j.length >= 2) {
				result++;
			}
		}
	}

	return result;
}

function checkVertical(arr) {
	result = 0;
	const tmp = Array(arr.length)
		.fill(0)
		.map(() => '');

	for (let i = 0; i < arr[0].length; i++) {
		for (let j = 0; j < arr.length; j++) {
			tmp[j] += arr[i][j];
		}
	}

	for (let i of tmp) {
		const split = i.split('X');

		for (let j of split) {
			if (j.length >= 2) {
				result++;
			}
		}
	}

	return result;
}

function solution() {
	const answer = [];
	answer.push(checkHorizontal(arr));
	answer.push(checkVertical(arr));
	console.log(answer.join(' '));
}

solution();
