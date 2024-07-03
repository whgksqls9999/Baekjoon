const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let arr = input[1].split(' ').map(Number);

function GCD(a, b) {
	let c;
	while (b !== 0) {
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

function LCM(a, b) {
	return (a * b) / GCD(a, b);
}

function cal(a, b) {
	let lcm = LCM(a, b);
	return `${lcm / b}/${lcm / a}\n`;
}

function solution() {
	let n = arr[0];
	let answer = '';

	for (let i = 1; i < arr.length; i++) {
		answer += cal(n, arr[i]);
	}

	console.log(answer.trim());
}

solution();
