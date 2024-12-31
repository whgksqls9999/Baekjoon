const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = 0;

	const [N, M] = input[idx++].split(' ').map(Number);
	const words = input.slice(idx).map((it) => it.trim());

	const map = new Map();

	let a = 'a'.codePointAt(0);
	let z = 'z'.codePointAt(0);
	for (let i = a; i <= z; i++) {
		map.set(String.fromCodePoint(i), false);
	}

	const essential = new Set([
		a,
		'n'.codePointAt(0),
		't'.codePointAt(0),
		'i'.codePointAt(0),
		'c'.codePointAt(0),
	]);

	function select(teached_words, start_point) {
		if (teached_words === M) {
			let res = 0;
			outer: for (const word of words) {
				for (const char of word) {
					if (map.get(char) === false) continue outer;
				}
				res++;
			}
			answer = Math.max(answer, res);
			return;
		}

		for (let i = start_point; i <= z; i++) {
			if (essential.has(i)) continue;

			if (map.get(String.fromCodePoint(i)) === false) {
				map.set(String.fromCodePoint(i), true);
				select(teached_words + 1, i + 1);
				map.set(String.fromCodePoint(i), false);
			}
		}
	}

	if (M < 5) {
		return 0;
	} else {
		essential.forEach((it) => {
			map.set(String.fromCodePoint(it), true);
		});
		select(5, a);
	}

	return answer;
}

console.log(solution());
