const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const [N, M] = input[idx++].split(' ').map(Number);

	const arr1 = Array(N)
		.fill()
		.map(() => input[idx++].trim().split('').map(Number));

	const arr2 = Array(N)
		.fill()
		.map(() => input[idx++].trim().split('').map(Number));

	if (N < 3 || M < 3) {
		if (arr1.join(' ') !== arr2.join(' ')) {
			return -1;
		}
	}

	for (let i = N - 1; i >= 2; i--) {
		for (let j = M - 1; j >= 2; j--) {
			if (arr1[i][j] !== arr2[i][j]) {
				change(arr1, i, j);
				answer++;
			}
		}
	}

	if (arr1.join(' ') !== arr2.join(' ')) {
		return -1;
	}

	return answer;
}

function change(arr, r, c) {
	for (let i = r - 2; i <= r; i++) {
		for (let j = c - 2; j <= c; j++) {
			arr[i][j] = (arr[i][j] + 1) % 2;
		}
	}
}

console.log(solution());
