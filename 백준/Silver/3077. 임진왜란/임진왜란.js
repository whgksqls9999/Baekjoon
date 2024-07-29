'use strict';

const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input[idx++].trim().split(' ');
	let arr2 = input[idx++].trim().split(' ');

	let answer = 0;

	let map = new Map();

	for (let i = 0; i < arr.length; i++) {
		map.set(arr[i], i);
	}

	for (let i = 0; i < arr2.length - 1; i++) {
		for (let j = i + 1; j < arr2.length; j++) {
			if (map.get(arr2[i]) < map.get(arr2[j])) {
				answer++;
			}
		}
	}

	return `${answer}/${(N * (N - 1)) / 2}`;
}

console.log(solution());
