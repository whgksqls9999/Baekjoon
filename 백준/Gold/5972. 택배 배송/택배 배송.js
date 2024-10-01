const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

class PriorityQueue {
  constructor(comparerer) {
    this.storage = [null];
    this.comparerer = comparerer;
  }

  isEmpty() {
    return this.storage.length === 1;
  }

  push(value) {
    this.storage.push(value);
    this.heapifyUp();
  }

  poll() {
    if (this.isEmpty()) return null;
    const result = this.storage[1];
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
      if (childIdx + 1 < storage.length && this.comparerer(storage[childIdx], storage[childIdx + 1])) {
        childIdx++;
      }

      if (this.comparerer(storage[parentIdx], storage[childIdx])) {
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
      if (this.comparerer(storage[parentIdx], storage[childIdx])) {
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
  
  const [N, M] = input[idx++].split(' ').map(Number);
  const edges = Array(N+1).fill().map(() => []);
  for (let i = 0; i < M; i++){
    const [s, e, c] = input[idx++].split(' ').map(Number);
    edges[s].push([e, c]);
    edges[e].push([s, c]);
  }
  const INF = 50_001 * 1_000;
  const visited = Array(N+1).fill(INF);
  const pq = new PriorityQueue((a,b) => a[1] > b[1]);
  pq.push([1, 0]);
  visited[1] = 0;
  while(!pq.isEmpty()){
    let cur = pq.poll();

    for (let [e, c] of edges[cur[0]]){
      let std = visited[cur[0]] + c;
      if (visited[e] > std){
        visited[e] = std;
        pq.push([e, std]);
      }
    }
  }

  return visited[N];
}

console.log(solution());
