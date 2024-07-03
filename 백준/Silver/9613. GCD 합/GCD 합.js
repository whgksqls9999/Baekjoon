const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n')
	.slice(1);

function GCD(a, b) {
	let c;
	while (b != 0) {
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

function LCM(a, b) {
	return (a * b) / GCD(a, b);
}

function solution() {
	let answer = '';

	for (let tc = 0; tc < input.length; tc++) {
		const cur = input[tc].split(' ').slice(1).map(Number);

		let n = 0;
		for (let i = 0; i < cur.length; i++) {
			for (let j = i + 1; j < cur.length; j++) {
				n += GCD(cur[i], cur[j]);
			}
		}
		answer += `${n}\n`;
	}

	console.log(answer.trim());
}

solution();
