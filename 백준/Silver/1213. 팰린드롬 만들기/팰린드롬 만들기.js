const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

let word = input[0].trim();

function isPalindrom(word) {
	for (let i = 0; i < Math.floor(word.length / 2); i++) {
		if (word[i] !== word[word.length - 1 - i]) {
			return false;
		}
	}
	return true;
}

function solution() {
	const chars = {};

	for (let key of word) {
		let curCode = key.codePointAt(0);
		if (!chars[curCode]) {
			chars[curCode] = 0;
		}
		chars[curCode]++;
	}

	let center = '';
	let half = '';
	for (let key in chars) {
		if (chars[key] % 2 !== 0) {
			if (center) {
				console.log("I'm Sorry Hansoo");
				return;
			}

			center = String.fromCodePoint(key);

			half += String.fromCodePoint(key).repeat((chars[key] - 1) / 2);
		} else {
			half += String.fromCodePoint(key).repeat(chars[key] / 2);
		}
	}
	console.log(`${half}${center}${[...half].reverse().join('')}`);
}

solution();
