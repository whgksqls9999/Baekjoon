const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

function solution() {
	let idx = 0;
	const answer = [];

	const T = Number(input[idx++]);
	for (let tc = 0; tc < T; tc++) {
		let N = Number(input[idx++]);

		const pre = Array(N + 1).fill(-1);
		const child = Array(N + 1)
			.fill()
			.map(() => []);
		const height = Array(N + 1).fill(0);

		for (let i = 1; i < N; i++) {
			const [a, b] = input[idx++].split(' ').map(Number);

			pre[b] = a;
			child[a].push(b);
		}

		let root = 0;
		for (let i = 1; i <= N; i++) {
			if (pre[i] === -1) {
				root = i;
				break;
			}
		}

		dfs(root, height, child);

		let [A, B] = input[idx++].split(' ').map(Number);

		if (height[A] > height[B]) {
			[A, B] = [B, A];
		}

		while (height[A] < height[B]) {
			B = pre[B];
		}

		while (A !== B) {
			A = pre[A];
			B = pre[B];
		}

		answer.push(A);
	}

	return answer.join('\n');
}

function dfs(n, height, child) {
	if (child[n].length === 0) return;

	for (let next of child[n]) {
		height[next] = height[n] + 1;
		dfs(next, height, child);
	}
}

console.log(solution());
