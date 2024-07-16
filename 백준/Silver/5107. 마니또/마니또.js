const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

let idx = 0;

function solution() {
	let answer = [];
	let tc = 0;
	while (true) {
		tc++;
		const N = +input[idx++];
		if (N === 0) break;
		const map = new Map();
		const visited = new Map();
		for (let i = 0; i < N; i++) {
			let [from, to] = input[idx++].trim().split(' ');

			map.set(from, to);
			visited.set(from, 0);
		}
		let cycle = 0;
		let check = 1;
		for (let i of visited.keys()) {
			if (visited.get(i) !== 0) continue;
			check++;

			let cur = i;
			visited.set(i, check);
			while (true) {
				let next = map.get(cur);

				if (visited.get(next) === 0) {
					visited.set(next, check);
				} else if (visited.get(next) === check) {
					cycle++;
					break;
				}

				cur = next;
			}
		}
		answer.push(`${tc} ${cycle}`);
	}
	return answer.join('\n');
}

let answer = solution();
console.log(answer);
