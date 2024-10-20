'use strict';

const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

function solution() {
	let idx = 0;

	let N = Number(input[idx++]);
	const sharks = Array(N)
		.fill()
		.map((_, index) => {
			let info = input[idx++].split(' ').map(Number);
			let shark = {
				size: info[0],
				velocity: info[1],
				intel: info[2],
			};

			return shark;
		});

	const matching = Array(N).fill(-1);

	for (let i = 0; i < N; i++) {
		for (let j = 0; j < 2; j++) {
			const picked = Array(N).fill(false);
			dfs(i, sharks, matching, picked, i);
		}
	}

	return matching.filter((it) => it === -1).length;
}

function dfs(cur, sharks, matching, picked, origin) {
	outer: for (let i = 0; i < sharks.length; i++) {
		if (cur === i) continue;
		if (picked[i]) continue;

		let check = 0; // 1: < . 2: ===
		for (let key in sharks[cur]) {
			if (sharks[cur][key] < sharks[i][key]) {
				continue outer;
			} else if (sharks[cur][key] === sharks[i][key]) {
				check++;
			}
		}

		if (check === 3 && cur < i) continue;

		picked[i] = true;

		if (
			matching[i] === -1 ||
			dfs(matching[i], sharks, matching, picked, origin)
		) {
			matching[i] = cur;
			return true;
		}
	}

	return false;
}

console.log(solution());
