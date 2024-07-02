const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n').slice(1);

let answer = '';

for (let query of input) {
	const [char, str] = query.split(' ');
	const set = new Set(char);
	let result = 'YES\n';

	if (set.size > new Set(str.trim()).size) {
		answer += 'NO\n';
		continue;
	}

	for (let i of str.trim()) {
		if (!set.has(i)) {
			result = 'NO\n';
			break;
		}
	}

	answer += result;
}

console.log(answer.trim());