const input = require('fs')
	.readFileSync(0, 'utf-8')
	.toString()
	.trim()
	.split('\n');

let idx = 0;

function solution() {
	const set = new Set();
	const answer = {
		P: 13,
		K: 13,
		H: 13,
		T: 13,
	};

	for (let i = 0; i < input[0].length; i += 3) {
		const cur = input[0].slice(i, i + 3);
		const type = cur[0];
		const num = +cur.slice(1);

		if (set.has(cur)) {
			return 'GRESKA';
		}
		answer[type]--;
		set.add(cur);
	}

	let tmp = [];
	for (let i in answer) {
		tmp.push(answer[i]);
	}
	return tmp.join(' ');
}

let answer = solution();
console.log(answer);
