const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const T = Number(input[idx++]);

	for (let tc = 0; tc < T; tc++) {
		let [n, m] = input[idx++].split(' ').map(Number);

		const prizes = [];

		for (let i = 0; i < n; i++) {
			const info = input[idx++].split(' ').map(Number);

			const stickers = info.slice(1, info.length - 1);
			const amount = info[info.length - 1];

			prizes.push({
				stickers,
				amount,
			});
		}

		prizes.sort((a, b) => b.amount - a.amount);

		const stickers = [0].concat(input[idx++].split(' ').map(Number));

		let res = 0;

		for (const prize of prizes) {
			const prizeStickers = prize.stickers;

			let min = Number.MAX_SAFE_INTEGER;

			for (let cur of prizeStickers) {
				min = Math.min(stickers[cur], min);
			}

			res += prize.amount * min;
		}

		answer.push(res);
	}

	return answer.join('\n');
}

console.log(solution());
