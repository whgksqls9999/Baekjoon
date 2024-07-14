const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const word = input[idx++].trim();
const dp = Array(word.length)
	.fill()
	.map(() => Array(26).fill(0));
for (let i = 0; i < word.length; i++) {
	const cur = word[i];

	dp[i][cur.codePointAt(0) - 'a'.codePointAt(0)]++;
}

for (let i = 1; i < dp.length; i++) {
	for (let j = 0; j < dp[i].length; j++) {
		dp[i][j] = dp[i - 1][j] + dp[i][j];
	}
}
const T = +input[idx++];

function solution() {
	const answer = [];

	for (let i = 0; i < T; i++) {
		let [char, s, e] = input[idx++].trim().split(' ');
		char = char.codePointAt(0) - 'a'.codePointAt(0);
		s = +s;
		e = +e;

		answer.push(dp[e][char] - (dp[s - 1]?.[char] ?? 0));
	}
	console.log(answer.join('\n'));
}

solution();
