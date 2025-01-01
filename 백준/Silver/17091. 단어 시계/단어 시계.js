const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function resolveMinute(minute) {
	minute = Number(minute);

	let order = '';

	if (minute === 0) {
	} else if (minute > 30) {
		minute = 60 - minute;
		order = 'to';
	} else {
		order = 'past';
	}

	const resolve_map = {
		0: "o' clock",
		1: 'one minute',
		2: 'two minutes',
		3: 'three minutes',
		4: 'four minutes',
		5: 'five minutes',
		6: 'six minutes',
		7: 'seven minutes',
		8: 'eight minutes',
		9: 'nine minutes',
		10: 'ten minutes',
		11: 'eleven minutes',
		12: 'twelve minutes',
		13: 'thirteen minutes',
		14: 'fourteen minutes',
		15: 'quarter',
		16: 'sixteen minutes',
		17: 'seventeen minutes',
		18: 'eighteen minutes',
		19: 'nineteen minutes',
		20: 'twenty minutes',
		21: 'twenty one minutes',
		22: 'twenty two minutes',
		23: 'twenty three minutes',
		24: 'twenty four minutes',
		25: 'twenty five minutes',
		26: 'twenty six minutes',
		27: 'twenty seven minutes',
		28: 'twenty eight minutes',
		29: 'twenty nine minutes',
		30: 'half',
		31: 'thirty one minutes',
		32: 'thirty two minutes',
		33: 'thirty three minutes',
		34: 'thirty four minutes',
		35: 'thirty five minutes',
		36: 'thirty six minutes',
		37: 'thirty seven minutes',
		38: 'thirty nine minutes',
		39: 'thirty ten minutes',
		40: 'forty minutes',
		41: 'forty one minutes',
		42: 'forty two minutes',
		43: 'forty three minutes',
		44: 'forty four minutes',
		45: 'quarter',
		46: 'forty six minutes',
		47: 'forty seven minutes',
		48: 'forty nine minutes',
		49: 'forty ten minutes',
		50: 'fifty minutes',
		51: 'fifty one minutes',
		52: 'fifty two minutes',
		53: 'fifty three minutes',
		54: 'fifty four minutes',
		55: 'fifty five minutes',
		56: 'fifty six minutes',
		57: 'fifty seven minutes',
		58: 'fifty nine minutes',
		59: 'fifty ten minutes',
	};

	return [resolve_map[minute], order];
}

function resolveAnswer(hour, word) {
	const resolve_map = {
		0: 'twelve',
		1: 'one',
		2: 'two',
		3: 'three',
		4: 'four',
		5: 'five',
		6: 'six',
		7: 'seven',
		8: 'eight',
		9: 'nine',
		10: 'ten',
		11: 'eleven',
		12: 'twelve',
	};

	if (word.includes("o' clock")) {
		return `${resolve_map[hour]} ${word}`.trim();
	} else if (word.includes('past')) {
		return `${word} ${resolve_map[hour]}`;
	} else if (word.includes('to')) {
		let index = (hour + 1) % 12;

		return `${word} ${resolve_map[index]}`;
	}
}

function solution() {
	let idx = 0;
	let answer = [];

	const hour = Number(input[idx++]);
	const minute = Number(input[idx++]);

	const [MINUTE, ORDER] = resolveMinute(minute);
	answer.push(resolveAnswer(hour, `${MINUTE} ${ORDER}`.trim()));

	return answer.join('\n');
}

console.log(solution());
