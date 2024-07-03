const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const [N, A, B] = input[0].split(' ').map(Number);
let arr = Array(N).fill(0);
arr[A - 1] = 1;
arr[B - 1] = 1;

function sep(depth) {
	const tmp = Array(Math.ceil(arr.length / 2)).fill(0);

	for (let i = 0; i < arr.length; i += 2) {
		let sum = 0;
		for (let j = 0; j < 2; j++) {
			sum += arr[i + j] ?? 0;
		}

		if (sum === 2) {
			return depth;
		}
		tmp[i / 2] = sum;
	}

	arr = [...tmp];
}

function solution() {
	let answer;
	let depth = 1;

	while (!answer && depth < 50_001) {
		answer = sep(depth++);
	}

	console.log(answer ?? -1);
}

solution();
