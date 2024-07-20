const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input[idx++].split(' ').map(Number);
	let T = +input[idx++];
	let dp = Array(arr.length)
		.fill()
		.map(() => Array(arr.length).fill(false));

	for (let i = 0; i < dp[0].length; i++) {
		dp[0][i] = true;
	}

	if (N > 1) {
		for (let j = 0; j < dp[1].length - 1; j++) {
			if (arr[j] === arr[j + 1]) {
				dp[1][j] = true;
			}
		}

		for (let i = 2; i < dp.length; i++) {
			for (let j = 0; j < dp.length - i; j++) {
				if (dp[i - 2][j + 1] && arr[j] === arr[j + i]) {
					dp[i][j] = true;
				}
			}
		}
	}

	let answer = [];
	for (let i = 0; i < T; i++) {
		let [s, e] = input[idx++].split(' ').map(Number);

		let length = e - s;

		answer.push(dp[length][s - 1] ? 1 : 0);
	}

	return answer.join('\n');
}

console.log(solution());
