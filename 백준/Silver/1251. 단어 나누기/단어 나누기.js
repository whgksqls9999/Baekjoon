const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let str = input[idx].trim();

	let answer = 'z'.repeat(str.length);

	for (let i = 1; i <= str.length - 2; i++) {
		for (let j = i + 1; j <= str.length - 1; j++) {
			for (let k = j + 1; k <= str.length; k++) {
				let cur = [...str.slice(0, i)]
					.reverse()
					.concat(
						[...str.slice(i, j)]
							.reverse()
							.concat([...str.slice(j)].reverse())
					)
					.join('');

				answer = answer > cur ? cur : answer;
			}
		}
	}

	return answer;
}

console.log(solution());
