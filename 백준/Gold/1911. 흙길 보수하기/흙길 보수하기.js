const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const [N, L] = input[idx++].split(' ').map(Number);
	const arr = [];
	for (let i = 0; i < N; i++) {
		arr.push(input[idx++].split(' ').map(Number));
	}
	let answer = 0;

	arr.sort((a, b) => a[0] - b[0]);

	let sPoint = arr[0][0] ?? 0;

	for (let i of arr) {
		if (sPoint < i[0]) {
			sPoint = i[0];
		}

		while (sPoint < i[1]) {
			answer++;
			sPoint += L;
		}
	}

	return answer;
}

console.log(solution());
