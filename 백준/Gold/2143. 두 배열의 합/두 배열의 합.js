const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let T = +input[idx++];

function solution() {
	let answer = 0;

	idx++;
	const A = [0].concat(input[idx++].split(' ').map(Number));
	idx++;
	const B = [0].concat(input[idx++].split(' ').map(Number));

	for (let i = 1; i < A.length; i++) {
		A[i] = A[i - 1] + A[i];
	}

	for (let i = 1; i < B.length; i++) {
		B[i] = B[i - 1] + B[i];
	}

	const mapA = {};
	for (let i = 0; i < A.length - 1; i++) {
		for (let j = i + 1; j < A.length; j++) {
			let cur = A[j] - A[i];
			mapA[cur] = (mapA[cur] ?? 0) + 1;
		}
	}

	for (let i = 0; i < B.length - 1; i++) {
		for (let j = i + 1; j < B.length; j++) {
			let cur = B[j] - B[i];

			if (!mapA[T - cur]) continue;
			answer += mapA[T - cur];
		}
	}

	console.log(answer);
}

solution();
