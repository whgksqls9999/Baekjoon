'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

let max = Number.MIN_SAFE_INTEGER;
let min = Number.MAX_SAFE_INTEGER;

function dfs(nums, operators, depth, N, sel) {
	if (depth === N - 1) {
		let result = nums[0];

		let idx = 1;
		for (let operator of sel) {
			switch (operator) {
				case 0:
					result += nums[idx++];
					break;
				case 1:
					result -= nums[idx++];
					break;
				case 2:
					result *= nums[idx++];
					break;
				case 3:
					if (result < 0) {
						result *= -1;
						result = Math.floor(result / nums[idx++]);
						result *= -1;
						break;
					}
					result = Math.floor(result / nums[idx++]);
					break;
			}
		}

		max = Math.max(max, result);
		min = Math.min(min, result);
		return;
	}

	for (let i = 0; i < operators.length; i++) {
		if (operators[i] === 0) continue;

		operators[i]--;
		dfs(nums, operators, depth + 1, N, [...sel, i]);
		operators[i]++;
	}
}

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let nums = input[idx++].split(' ').map(Number);
	let operators = input[idx++].split(' ').map(Number);

	dfs(nums, operators, 0, N, []);

	return `${max}\n${min}`;
}

console.log(solution());
