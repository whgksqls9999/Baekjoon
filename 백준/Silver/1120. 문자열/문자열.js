const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, M] = input[idx].trim().split(' ');

	let answer = 50;
	for (let i = 0; i < M.length - N.length + 1; i++) {
		let cur = M.substring(0, i) + N + M.substring(i + N.length);
		let result = 0;

		for (let j = 0; j < cur.length; j++) {
			if (cur[j] !== M[j]) result++;
		}

		answer = Math.min(result, answer);
	}
	return answer;
}

console.log(solution());
