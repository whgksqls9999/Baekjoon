const file = process.platform === 'linux' ? '/dev/stdin' : 'example.txt';
const input = require('fs').readFileSync(file).toString().trim().split('\n');

function solution() {
	let idx = 0;

	const answer = [];
	for (let tc = 0; tc < input.length; tc++) {
		let [word, n] = input[idx++].split(' ');
		n = Number(n);

		const chars = word.split('');
		const used = Array(chars.length).fill(false);

		/** @type {{result: string, num: number}} */
		const permutation = permutationGen(chars, used, n);

		permutation('', 0);

		answer.push(`${word} ${n} = ${permutation.result}`);
	}

	return answer.join('\n');
}

function permutationGen(chars, used, n) {
	let num = 0;

	function permutation(word, depth) {
		if (word.length === chars.length) {
			permutation.num = ++num;
			if (num === n) {
				permutation.result = word;
			}
			return;
		}

		for (let i = 0; i < chars.length; i++) {
			if (used[i]) continue;

			used[i] = true;
			permutation(word + chars[i], depth + 1);
			used[i] = false;
		}
	}

	permutation.result = 'No permutation';

	return permutation;
}

console.log(solution());
