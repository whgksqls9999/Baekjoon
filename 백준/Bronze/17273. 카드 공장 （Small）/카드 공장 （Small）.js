const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const [N, M] = input[idx++].split(' ').map(Number);
	const cards = [];
	for (let i = 0; i < N; i++) {
		const [A, B] = input[idx++].split(' ').map(Number);

		const card = {
			numbers: [A, B],
			index: 0,
		};

		cards.push(card);
	}

	for (let i = 0; i < M; i++) {
		const order = Number(input[idx++]);

		for (const card of cards) {
			if (card.numbers[card.index] <= order) {
				card.index = (card.index + 1) % 2;
			}
		}
	}

	answer.push(
		cards.reduce((prev, card) => {
			return prev + card.numbers[card.index];
		}, 0)
	);

	return answer.join('');
}

console.log(solution());
