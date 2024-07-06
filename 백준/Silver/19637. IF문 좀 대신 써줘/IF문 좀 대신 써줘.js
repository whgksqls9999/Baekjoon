const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [N, M] = input[idx++].split(' ').map(Number);

const boundary = Array(2)
	.fill(0)
	.map(() => []);

for (let i = 0; i < N; i++) {
	const cur = input[idx++].trim().split(' ');
	boundary[0].push(cur[0]);
	boundary[1].push(+cur[1]);
}

function solution() {
	const answer = [];
	for (let j = 0; j < M; j++) {
		let cur = +input[idx++];

		let l = 0;
		let r = boundary[0].length - 1;

		while (l < r) {
			let mid = Math.floor((l + r) / 2);
			let cls = boundary[1][mid];

			if (cur <= cls) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}

		answer.push(boundary[0][r]);
	}
	console.log(answer.join('\n'));
}

solution();
