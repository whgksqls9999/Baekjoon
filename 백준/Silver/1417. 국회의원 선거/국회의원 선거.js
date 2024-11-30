const input = require('fs')
	.readFileSync('/dev/stdin')
	.toString()
	.trim()
	.split('\n');

class PriorityQueue {
	constructor(comparer) {
		this.storage = [null];
		this.comparer = comparer;
	}

	isEmpty() {
		return this.storage.length === 1;
	}

	push(value) {
		const storage = this.storage;
		storage.push(value);
		this.heapifyUp();
	}

	poll() {
		if (this.isEmpty()) return null;
		const storage = this.storage;
		const result = storage[1];

		this.heapifyDown();
		return result;
	}

	heapifyDown() {
		const storage = this.storage;
		if (storage.length === 2) {
			storage.pop();
			return;
		}
		storage[1] = storage.pop();

		let parentIdx = 1;
		let childIdx = 2;
		while (childIdx < storage.length) {
			if (
				childIdx + 1 < storage.length &&
				this.comparer(storage[childIdx], storage[childIdx + 1])
			) {
				childIdx++;
			}

			if (this.comparer(storage[parentIdx], storage[childIdx])) {
				[storage[parentIdx], storage[childIdx]] = [
					storage[childIdx],
					storage[parentIdx],
				];
			} else {
				break;
			}

			parentIdx = childIdx;
			childIdx *= 2;
		}
	}

	heapifyUp() {
		const storage = this.storage;

		if (storage.length === 2) return;

		let childIdx = storage.length - 1;
		let parentIdx = Math.floor(childIdx / 2);

		while (childIdx > 1) {
			if (this.comparer(storage[parentIdx], storage[childIdx])) {
				[storage[childIdx], storage[parentIdx]] = [
					storage[parentIdx],
					storage[childIdx],
				];
			} else {
				break;
			}

			childIdx = parentIdx;
			parentIdx = Math.floor(parentIdx / 2);
		}
	}
}

function solution() {
	let idx = 0;
	let answer = 0;

	const arr = input.slice(1).map((it) => Number(it));

	const pq = new PriorityQueue(
		(a, b) => a.value < b.value || a.priority < b.priority
	);

	let first = {
		value: arr[0],
		priority: 1,
	};

	arr.forEach((it, idx) => {
		if (idx === 0) {
			return;
		}

		pq.push({
			value: it,
			priority: idx === 0 ? 1 : 0,
		});
	});

	while (!pq.isEmpty()) {
		let cur = pq.poll();

		if (first.value <= cur.value) {
			answer++;
			first.value++;
			pq.push({
				value: cur.value - 1,
				priority: 0,
			});
		} else {
			break;
		}
	}

	return answer;
}

console.log(solution());
