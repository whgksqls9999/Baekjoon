const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let [N, M, B] = input[idx++].split(' ').map(Number);

	let arr = Array(N)
		.fill()
		.map(() => input[idx++].split(' ').map(Number));

	let answer = [Number.MAX_SAFE_INTEGER, -1];

	for (let i = 256; i >= 0; i--) {
		let time = 0;
		let rest = B;

		for (let j = 0; j < N; j++) {
			for (let k = 0; k < M; k++) {
				if (arr[j][k] > i) {
					time += (arr[j][k] - i) * 2;
					rest += arr[j][k] - i;
				} else if (arr[j][k] === i) {
					continue;
				} else {
					rest -= i - arr[j][k];
					time += i - arr[j][k];
				}
			}
		}
		if (rest < 0) continue;

		if (answer[0] > time) {
			answer = [time, i];
		}
	} // outer

	return answer.join(' ');
}

console.log(solution());
