const file = process.platform === 'linux' ? '/dev/stdin' : 'example.txt';
const input = require('fs').readFileSync(file).toString().trim().split('\n');

function solution() {
	let idx = 0;
	const [N, S, M] = input[idx++].split(' ').map(Number);
	const gap = input[idx++].split(' ').map(Number);

	const arr = Array(N + 1)
		.fill()
		.map(() => []);
	arr[0].push(S);

	for (let i = 0; i < N; i++) {
		arr[i] = filter(arr[i], gap[i], M);
		arr[i + 1] = calc(arr[i], gap[i], M);
	}

	const resultArr = arr[arr.length - 1];

	return resultArr.length > 0 ? Math.max(...resultArr) : -1;
}

function filter(arr, diff, limit) {
	return Array.from(new Set(arr));
	let min = Number.MAX_SAFE_INTEGER;
	let max = Number.MIN_SAFE_INTEGER;

	for (let num of arr) {
		if (num + diff <= limit) {
			max = Math.max(max, num);
		}
		if (num - diff >= 0) {
			min = Math.min(min, num);
		}
	}

	const result = [];
	if (min !== Number.MAX_SAFE_INTEGER) {
		result.push(min);
	}

	if (max !== Number.MIN_SAFE_INTEGER && min !== max) {
		result.push(max);
	}

	return result;
}

function calc(arr, diff, limit) {
	const result = [];

	for (let num of arr) {
		if (num + diff <= limit) result.push(num + diff);
		if (num - diff >= 0) result.push(num - diff);
	}

	return result;
}

console.log(solution());
