const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

const arr = input[1].split(' ');
let max = 0;
let min = Number.MAX_SAFE_INTEGER;

function check(a, b, c) {
	let action = {
		'>': function (a, b) {
			return a > b;
		},
		'<': function (a, b) {
			return a < b;
		},
	};

	return action[c](a, b);
}
function dfs(_arr, depth, used) {
	if (depth === arr.length + 1) {
		let cur = _arr.join('');

		if (cur > max) {
			max = cur;
		}
		if (cur < min) {
			min = cur;
		}
		return;
	}

	for (let i = 0; i <= 9; i++) {
		if (used[i]) continue;
		if (depth > 0) {
			if (!check(_arr[_arr.length - 1], i, arr[_arr.length - 1]))
				continue;
		}
		used[i] = true;
		dfs([..._arr, i], depth + 1, used);
		used[i] = false;
	}
}
function solution() {
	let answer = [];
	dfs([], 0, Array(10).fill(false));
	console.log(max + '\n' + min);
}

solution();
