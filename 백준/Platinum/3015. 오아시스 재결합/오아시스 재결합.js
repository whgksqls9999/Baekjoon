const input = require('fs')
	.readFileSync(0)
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	let N = +input[idx++];
	let arr = input.slice(idx).map((it) => [+it, 1]);

	let stack = [];
	let cnt = 0;

	arr.forEach((it) => {
		let peek = stack[stack.length - 1];
		let num = 0;

		let [curIt, curNum] = it;

		if (peek) {
			num = peek[1];
			peek = peek[0];
		}

		if (peek === undefined) {
			stack.push(it);
		} else if (peek > curIt) {
			stack.push([curIt, curNum]);
			cnt++;
		} else if (peek <= curIt) {
			let max = peek;

			while (peek !== undefined) {
				if (peek < max) {
					stack.pop();
				} else if (peek === max) {
					cnt += num;
					if (max === curIt) {
						curNum += num;
						stack.pop();
					} else {
						stack.pop();
					}
				} else if (peek > curIt) {
					cnt++;
					// cnt += (num);
					break;
				} else if (peek === curIt) {
					cnt += num;
					curNum += num;
					max = peek;
					stack.pop();
				} else if (peek < curIt) {
					max = Math.max(max, peek);
					cnt += num;
					stack.pop();
				}

				peek = stack[stack.length - 1];
				if (peek) {
					num = peek[1];
					peek = peek[0];
				}
			}
			// while (tmp.length !== 0) {
			// 	stack.push(tmp.pop());
			// }
			stack.push([curIt, curNum]);
		}
		// console.log(cnt, stack, curIt);
	});
	return cnt.toString();
}

console.log(solution());
