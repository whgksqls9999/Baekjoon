const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

function parsingArr() {
	let idx = 0;
	const result = [];

	for (let i = 0; i < 2; i++) {
		const [N, M] = input[idx++].split(' ').map(Number);
		const arr = [];
		for (let j = 0; j < N; j++) {
			arr.push(input[idx++].split(' ').map(Number));
		}
		result.push(arr);
	}

	return result;
}

function calArr(a, b) {
	const result = Array(a.length)
		.fill(0)
		.map(() => Array(b[0].length).fill(0));

	for (let i = 0; i < result.length; i++) {
		for (let j = 0; j < result[i].length; j++) {
			let sum = 0;

			for (let k = 0; k < a[i].length; k++) {
				sum += a[i][k] * b[k][j];
			}

			result[i][j] = sum;
		}
	}

	return result;
}

const [arr1, arr2] = parsingArr();

const result = calArr(arr1, arr2);

console.log(result.map((it) => it.join(' ')).join('\n'));