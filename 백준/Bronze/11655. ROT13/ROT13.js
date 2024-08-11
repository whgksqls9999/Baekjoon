'use strict';

const input = require('fs').readFileSync(0).toString().split('\n');

function solution() {
	const str = input[0];

	const code_A = 'A'.codePointAt(0);
	const code_Z = 'Z'.codePointAt(0);
	const code_a = 'a'.codePointAt(0);
	const code_z = 'z'.codePointAt(0);

	const strArr = Array(str.length);

	for (let it of str) {
		const code_it = it.codePointAt(0);

		let charToInsert = it;

		if (code_it >= code_A && code_it <= code_Z) {
			charToInsert = String.fromCodePoint(
				code_it + 13 > code_Z
					? code_it + 13 - (code_Z - code_A + 1)
					: code_it + 13
			);
		} else if (code_it >= code_a && code_it <= code_z) {
			charToInsert = String.fromCodePoint(
				code_it + 13 > code_z
					? code_it + 13 - (code_z - code_a + 1)
					: code_it + 13
			);
		}

		strArr.push(charToInsert);
	}

	return strArr.join('');
}

console.log(solution());
