const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
const arr = input.map((it) => it.trim().split('').map(Number));
const visited_i = Array(input.length)
	.fill()
	.map(() => []);
const visited_j = Array(input.length)
	.fill()
	.map(() => []);
const visited_k = Array(input.length)
	.fill()
	.map(() => []);
let answer = '';

function dfs(depth, zero) {
	if (depth === zero.length) {
		answer = arr.map((it) => it.join('')).join('\n');
		return;
	}

	let [r, c] = zero[depth];

	let check = false;
	for (let i = 1; i <= 9; i++) {
		if (
			visited_i[r][i] ||
			visited_j[c][i] ||
			visited_k[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i]
		)
			continue;

		arr[r][c] = i;
		visited_i[r][i] = true;
		visited_j[c][i] = true;
		visited_k[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i] = true;
		dfs(depth + 1, zero);
		if (answer) return;
		visited_k[Math.floor(r / 3) * 3 + Math.floor(c / 3)][i] = false;
		visited_i[r][i] = false;
		visited_j[c][i] = false;
		arr[r][c] = 0;
	}
}
function solution() {
	const zero = [];
	for (let i = 0; i < arr.length; i++) {
		for (let j = 0; j < arr.length; j++) {
			let cur = arr[i][j];
			if (cur === 0) {
				zero.push([i, j]);
				continue;
			}
			visited_i[i][cur] = true;
			visited_j[j][cur] = true;
		}
	}
	let idx = 0;
	for (let i = 0; i < 3; i++) {
		for (let j = 0; j < 3; j++) {
			for (let k = i * 3; k < i * 3 + 3; k++) {
				for (let l = j * 3; l < j * 3 + 3; l++) {
					let cur = arr[k][l];
					if (cur === 0) continue;

					visited_k[idx][cur] = true;
				}
			}
			idx++;
		}
	}

	dfs(0, zero);
	console.log(answer);
}

solution();
