'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input[idx++].split(' ').map(Number);

	let answer = 0;

	function dfs(curArr, energy) {
		answer = Math.max(answer, energy);

		if (curArr.length === 2) return;

		for (let i = 1; i < curArr.length - 1; i++) {
			dfs(
				curArr.slice(0, i).concat(curArr.slice(i + 1)),
				energy + curArr[i - 1] * curArr[i + 1]
			);
		}
	}

	dfs(arr, 0);

	return answer;
}

console.log(solution());
