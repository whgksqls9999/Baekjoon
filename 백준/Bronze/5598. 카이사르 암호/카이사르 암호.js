const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let answer = [];

	const before = 'A B C D E F G H I J K L M N O P Q R S T U V W X Y Z'.split(
		' '
	);
	const after = 'D E F G H I J K L M N O P Q R S T U V W X Y Z A B C'.split(
		' '
	);
	const map = [];

	for (let i = 0; i < after.length; i++) {
		map.push([after[i], before[i]]);
	}

	const mmap = new Map(map);

	const word = input[idx++].trim();

	for (let char of word) {
		answer.push(mmap.get(char));
	}

	return answer.join('');
}

console.log(solution());
