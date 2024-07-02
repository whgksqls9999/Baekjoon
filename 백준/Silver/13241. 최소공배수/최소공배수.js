const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

const arr = input[0].split(' ').map(Number);

let max = Math.max(...arr);
let min = Math.min(...arr);

function GCD(A, B) {
	if (B === 0) return A;

	let max = Math.max(A, B);
	let min = Math.min(A, B);

	return GCD(min, max % min);
}

console.log((max * min) / GCD(max, min));
