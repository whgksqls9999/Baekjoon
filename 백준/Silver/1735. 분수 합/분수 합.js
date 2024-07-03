const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

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

let A = input[0].split(' ').map(Number);
let B = input[1].split(' ').map(Number);

function solution() {
	let base = LCM(A[1], B[1]);
	let top = (A[0] * base) / A[1] + (B[0] * base) / B[1];
	let gcd = GCD(base, top);

	let result = `${top / gcd} ${base / gcd}`;

	console.log(result);
}

solution();
