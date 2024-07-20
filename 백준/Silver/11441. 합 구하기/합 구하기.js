const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = [0].concat(input[idx++].split(' ').map(Number));
	let T = +input[idx++];

	let prefixSum = [0];
	for (let i = 1; i < arr.length; i++) {
		prefixSum[i] = prefixSum[i - 1] + arr[i];
	}

	let answer = [];
	for (let i = 0; i < T; i++) {
		let [s, e] = input[idx++].split(' ').map(Number);

		answer.push(prefixSum[e] - prefixSum[s - 1]);
	}

	return answer.join('\n');
}

console.log(solution());
