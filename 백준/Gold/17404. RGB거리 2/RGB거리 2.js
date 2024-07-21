const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input.slice(idx++).map((it) => it.split(' ').map(Number));
	let dp = Array(3)
		.fill()
		.map((it) =>
			Array(arr.length)
				.fill()
				.map(() => Array(3))
		);

	for (let Q = 0; Q < 3; Q++) {
		for (let i = 0; i < 3; i++) {
			if (Q === i) {
				dp[Q][0][i] = arr[0][i];
			} else {
				dp[Q][0][i] = Number.MAX_SAFE_INTEGER - 1000 * 1001;
			}
		}
	}

	for (let Q = 0; Q < 3; Q++) {
		for (let j = 1; j < N - 1; j++) {
			for (let i = 0; i < 3; i++) {
				dp[Q][j][i] =
					arr[j][i] +
					Math.min(
						dp[Q][j - 1][(i + 1) % 3],
						dp[Q][j - 1][(i + 2) % 3]
					);
			}
		}
	}
	for (let Q = 0; Q < 3; Q++) {
		for (let i = 0; i < 3; i++) {
			if (Q === i) {
				dp[Q][N - 1][i] = Number.MAX_SAFE_INTEGER;
				continue;
			}
			dp[Q][N - 1][i] = Number.MAX_SAFE_INTEGER;

			for (let k = 1; k <= 2; k++) {
				dp[Q][N - 1][i] = Math.min(
					dp[Q][N - 2][(i + k) % 3] + arr[N - 1][i],
					dp[Q][N - 1][i]
				);
			}
		}
	}

	// console.table(dp);
	let answer = Number.MAX_SAFE_INTEGER;
	for (let Q = 0; Q < 3; Q++) {
		for (let i = 0; i < 3; i++) {
			answer = Math.min(dp[Q][dp[Q].length - 1][i], answer);
		}
	}
	return answer;
}

console.log(solution());
