const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let [N, M] = input[0].split(' ').map(Number);
let minPackage = Infinity;
let minSingle = Infinity;

const arr = input.slice(1);
for (let string of arr) {
	let [package, single] = string.split(' ').map(Number);

	minPackage = Math.min(package, minPackage);
	minSingle = Math.min(single, minSingle);
}

function solution() {
	if (minPackage < minSingle * 6) {
		if (minPackage < minSingle * (N % 6)) {
			console.log(minPackage * Math.ceil(N / 6));
		} else {
			console.log(minPackage * Math.floor(N / 6) + minSingle * (N % 6));
		}
	} else {
		console.log(minSingle * N);
	}
}

solution();
