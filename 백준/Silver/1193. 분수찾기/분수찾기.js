const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

function solution() {
	let N = Number(input[0]);

	for (let i = 1; true; i++) {
		if (N > i) {
			N -= i;
		} else {
			if (i % 2 === 0) {
				return `${N}/${i - N + 1}`;
			} else {
				return `${i - N + 1}/${N}`;
			}
		}
	}
}

console.log(solution());