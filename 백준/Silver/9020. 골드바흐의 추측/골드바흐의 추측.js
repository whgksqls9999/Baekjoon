const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n')
	.map(Number);

const [T, ...queries] = input;

const arr = Array(10001)
	.fill(0)
	.map((it, idx) => idx);

function prime() {
	for (let i = 2; i <= Math.sqrt(arr.length); i++) {
		if (arr[i] === i) {
			arr[i] = 1;
			for (let j = i + i; j < arr.length; j += i) {
				arr[j] = 0;
			}
		}
	}
}

prime();

function solution() {
	let answer = '';

	for (let key of queries) {
		let l = key / 2;
		let r = key / 2;

		while (!(arr[l] && arr[r])) {
			l--;
			r++;
		}

		answer += `${l} ${r}\n`;
	}

	console.log(answer.trim());
}

solution();
