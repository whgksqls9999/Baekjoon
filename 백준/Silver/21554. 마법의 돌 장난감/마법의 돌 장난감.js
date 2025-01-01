const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function findSpot(arr) {
	let l = -1;
	let r = -1;

	outer: for (let i = 0; i < arr.length - 1; i++) {
		if (arr[i] > arr[i + 1]) {
			l = i;

			for (let j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					r = j;
				} else {
					break outer;
				}
			}
		}
	}

	return [l, r];
}
function manipulate(arr, l, r) {
	const replicated_arr = arr.slice(l, r + 1);

	for (let i = l; i <= r; i++) {
		arr[i] = replicated_arr[replicated_arr.length - 1 - i + l];
	}
}

manipulate.count = 0;

function solution() {
	let idx = 0;
	let answer = [];

	const N = Number(input[idx++]);
	const arr = input[idx++].split(' ').map(Number);
	const orders = [];

	while (manipulate.count < 100) {
		const [l, r] = findSpot(arr);

		if (l === -1) break;
		orders.push([l + 1, r + 1]);
		manipulate(arr, l, r);
		manipulate.count++;
	}

	answer.push(manipulate.count);
	answer.push(...orders);

	return answer.map((it) => it.join?.(' ') ?? it).join('\n');
}

console.log(solution());
