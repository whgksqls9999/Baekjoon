const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let N = +input[idx];
const prime = Array(1_003_002)
	.fill(0)
	.map((it, idx) => idx);
for (let i = 2; i <= Math.sqrt(prime.length); i++) {
	if (prime[i] !== i) continue;

	for (let j = i * 2; j < prime.length; j += i) {
		prime[j] = -1;
	}
}
prime[1] = -1;

function isPalindrome(a) {
	a = a.toString();

	for (let i = 0; i < Math.ceil(a.length / 2); i++) {
		if (a[i] !== a[a.length - 1 - i]) {
			return false;
		}
	}
	return true;
}

function solution() {
	for (let i = N; true; i++) {
		if (prime[i] === -1) continue;
		if (!isPalindrome(i)) continue;
		console.log(i);
		break;
	}
}

solution();
