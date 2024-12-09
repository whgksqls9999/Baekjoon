const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const [N, M] = input[idx++].split(' ').map(Number);
	const J = Number(input[idx++]);
	const arr = input.slice(idx).map(Number);

	let r = M;
	for (let i of arr) {
		if (r < i) {
			answer += i - r;
			r = i;
		} else if (i <= r - M) {
			answer += r - M - i + 1;
			r = i + M - 1;
		}
	}

	return answer;
}

console.log(solution());
