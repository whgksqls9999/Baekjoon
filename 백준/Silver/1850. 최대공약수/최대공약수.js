const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [N, M] = input[idx].split(' ').map(BigInt);

function gcd(a, b) {
	let c;
	while (b != 0) {
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

function solution() {
	console.log('1'.repeat(Number(gcd(N, M))));
}

solution();
