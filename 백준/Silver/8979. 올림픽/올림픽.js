const input = require('fs')
	.readFileSync('dev/stdin')
	.toString()
	.trim()
	.split('\n');

const countries = [];

function compare(a, b) {
	for (let i = 0; i < a.medal.length; i++) {
		let aCur = +a.medal[i];
		let bCur = +b.medal[i];

		if (aCur > bCur) {
			return -1;
		} else if (aCur < bCur) {
			return 1;
		}
	}

	return 0;
}

function returnAnswer(arr, N) {
	for (let cur of arr) {
		if (cur.num === N) {
			return cur.rank;
		}
	}
}
const N = +input[0].split(' ')[1];

for (let i = 1; i < input.length; i++) {
	const cur = input[i].trim().split(' ');
	countries.push({
		num: +cur[0],
		medal: cur.slice(1, cur.length),
	});
}

countries.sort((a, b) => compare(a, b));
countries[0].rank = 1;

for (let i = 1; i < countries.length; i++) {
	let cur = countries[i];

	if (!compare(cur, countries[i - 1])) {
		cur.rank = countries[i - 1].rank;
	} else {
		cur.rank = i + 1;
	}
}

console.log(returnAnswer(countries, N));
