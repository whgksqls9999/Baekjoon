const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function getBit(char) {
	switch (typeof char) {
		case 'string':
			return 1 << (char.codePointAt(0) - 'a'.codePointAt(0));
		case 'number':
			return 1 << (char - 'a'.codePointAt(0));
	}
}

function solution() {
	let idx = 0;
	let answer = 0;

	const [N, M] = input[idx++].split(' ').map(Number);
	if (M < 5) return 0;

	const words = input.slice(idx).map((it) => {
		let word = it.trim();
		let res = 0;
		for (const char of word) {
			res |= getBit(char);
		}
		return res;
	});

	let base_teached_word = 0;
	base_teached_word |= getBit('a');
	base_teached_word |= getBit('n');
	base_teached_word |= getBit('t');
	base_teached_word |= getBit('i');
	base_teached_word |= getBit('c');

	function select(teached_word, depth, idx, M) {
		if (depth === M) {
			let res = 0;
			for (const word of words) {
				if ((teached_word & word) === word) {
					res++;
				}
			}
			answer = Math.max(answer, res);
			return;
		}

		for (let start = idx, end = 'z'.codePointAt(0); start <= end; start++) {
			const cur = getBit(start);
			if ((cur & base_teached_word) >= 1) continue;
			if ((cur & teached_word) !== cur) {
				select(teached_word | cur, depth + 1, start + 1, M);
			}
		}
	}

	select(base_teached_word, 5, 'a'.codePointAt(0), M);

	return answer;
}

console.log(solution());
