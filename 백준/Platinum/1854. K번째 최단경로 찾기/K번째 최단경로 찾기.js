const input = require('fs')
  .readFileSync(0)
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
      if (childIdx + 1 < storage.length && this.comparer(storage[childIdx], storage[childIdx + 1])) {
        childIdx++;
      }

      if (this.comparer(storage[parentIdx], storage[childIdx])) {
        [storage[parentIdx], storage[childIdx]] = [storage[childIdx], storage[parentIdx]];
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
        [storage[childIdx], storage[parentIdx]] = [storage[parentIdx], storage[childIdx]];
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
  const [n, m, k] = input[idx++].split(' ').map(Number);

  const INF = Number.MAX_SAFE_INTEGER;
  const distance = Array(n + 1).fill().map(() => []);

  const edges = Array(n + 1).fill().map(() => []);
  for (let i = 0; i < m; i++) {
    const [s, e, d] = input[idx++].split(' ').map(Number);
    edges[s].push([e, d]);
  }

  const pq = new PriorityQueue((a, b) => a[1] > b[1]);
  pq.push([1, 0]);

  let check = new Set();

  while (!pq.isEmpty() && check.size !== n) {
    let cur = pq.poll();

    if (distance[cur[0]].length > k) {
      check.add(cur[0]);
      continue;
    }

    distance[cur[0]].push(cur[1]);

    for (let [next, dist] of edges[cur[0]]) {
      let newD = cur[1] + dist;

      if ((distance[next][k - 1] ?? INF) > newD){
        pq.push([next, newD]);
      }
    }
  }
  
  const answer = [];
  for (let i = 1; i <= n; i++) {
    if (distance[i].length < k) {
      answer.push(-1);
    } else {
     answer.push(distance[i][k-1]);
    }
  }
  return answer.join('\n');
}

console.log(solution());
