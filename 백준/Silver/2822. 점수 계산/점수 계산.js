const input = require('fs')
	.readFileSync('dev/stdin')
	.toString()
	.trim()
	.split('\n');

const scores = input.map((it, idx) => ({
	score: Number(it),
	index: idx,
	toString() {
		return this.score;
	},
}));

scores.sort((a, b) => b - a);

const indexs = [];
const totalScore = scores.slice(0, 5).reduce((prev, cur) => {
	indexs.push(cur.index + 1);
	return prev + cur;
}, 0);

console.log(totalScore + '\n' + indexs.sort().join(' '));
