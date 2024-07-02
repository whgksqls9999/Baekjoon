const input = require('fs')
	.readFileSync('dev/stdin')
	.toString()
	.trim()
	.split('\n');

const countries = [];

const N = +input[0].split(' ')[1];

for (let i = 1; i < input.length; i++) {
	const cur = input[i].trim().split(' ');
	countries.push({
		num: +cur[0],
		medal: cur.slice(1, cur.length).join(' '),
		toString() {
			return this.medal;
		},
	});
}

countries.sort((a, b) => b.toString().localeCompare(a.toString()));

countries[0].rank = 1;

for (let i = 1; i < countries.length; i++) {
	let cur = countries[i];

	if (cur.toString() == countries[i - 1].toString()) {
		cur.rank = countries[i - 1].rank;
	} else {
		cur.rank = i + 1;
	}
}

for (let i = 0; i < countries.length; i++) {
	if (countries[i].num === N) {
		return console.log(countries[i].rank);
	}
}
