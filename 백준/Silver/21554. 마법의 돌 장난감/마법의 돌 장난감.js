const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function findSpot(arr) {
	let l = -1;
	let r = -1;

	const res = [];
	for (let i = 0; i < arr.length - 1; i++) {
		if (arr[i] > arr[i + 1]) {
			l = i;
			for (let j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					r = j;
				} else {
					break;
				}
			}

			if (l !== -1 && r !== -1) {
				res.push([l, r]);
				i = r + 1;
				l = r = -1;
			}
		}
	}

	return res;
}
function manipulate(arr, l, r) {
	const replicated_arr = arr.slice(l, r + 1);
	replicated_arr.reverse();
	arr.splice(l, r - l + 1, ...replicated_arr);
	manipulate.count++;
}

manipulate.count = 0;

function solution() {
	let idx = 0;
	let answer = [];

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	const orders = [];

	for (let i = 0; i < N; i++) {
		if (arr[i] === i + 1) continue;
		if (manipulate.count > 100) break;

		const right_position = arr.indexOf(i + 1);
		orders.push([i + 1, right_position + 1]);
		manipulate(arr, i, right_position);
	}

	if (manipulate.count >= 101) return -1;

	answer.push(manipulate.count);
	answer.push(...orders);

	return answer.map((it) => it.join?.(' ') ?? it).join('\n');
}

console.log(solution());
