const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [N, M] = input[idx++].split(' ').map(Number);

function solution() {
	const answer = [];
	const teamMap = new Map();
	const memberMap = new Map();

	for (let i = 0; i < N; i++) {
		const team = input[idx++].trim();
		const members = +input[idx++];

		const tmp = [];
		for (let j = 0; j < members; j++) {
			const member = input[idx++].trim();
			tmp.push(member);
			memberMap.set(member, team);
		}
		tmp.sort((a, b) => a.localeCompare(b));

		teamMap.set(team, new Set(tmp));
	}

	for (let i = 0; i < M; i++) {
		const word = input[idx++].trim();
		const type = +input[idx++];

		if (type === 1) {
			answer.push(memberMap.get(word));
		} else {
			let tmp = [];
			teamMap.get(word).forEach((it) => {
				tmp.push(it);
			});
			answer.push(tmp.join('\n'));
		}
	}
	console.log(answer.join('\n'));
}

solution();
