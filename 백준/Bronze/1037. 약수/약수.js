const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

const arr = input[1].split(' ').map(Number);
let max = 0;
let min = Infinity;
for (let i of arr) {
	if (i > max) {
		max = i;
	}
	if (i < min) {
		min = i;
	}
}
console.log(max * min);
