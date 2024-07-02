const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

const N = +input[0];

const arr = [];
for (let i = 1; i <= N; i++) {
	arr.push(i);
}

const answer = [];

for (let i = 0; i < arr.length; i++) {
	if (i % 2 === 1) {
		arr.push(arr[i]);
	} else {
		answer.push(arr[i]);
	}
}

console.log(answer.join(' '));