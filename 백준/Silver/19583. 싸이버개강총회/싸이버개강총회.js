const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let idx = 0;
let [s, e, se] = input[idx++].split(' ');

function solution() {
	let answer = 0;

	const set = new Set();
	while (idx < input.length) {
		const [time, name] = input[idx++].split(' ').map((it) => it.trim());

		if (time.localeCompare(s) <= 0) {
			set.add(name);
		} else if (
			time.localeCompare(e) >= 0 &&
			time.localeCompare(se) <= 0 &&
			set.has(name)
		) {
			set.delete(name);
			answer++;
		}
	}

	console.log(answer);
}

solution();
