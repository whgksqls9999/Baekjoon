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
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

function solution() {
  let idx = 0;
  let T = Number(input[idx++]);

  const answer = [];

  for (let tc = 0; tc < T; tc++){
    let [n, d, c] = input[idx++].split(' ').map(Number);
    
    const edges = Array(n+1).fill().map(() => []);
    for (let i = 0; i < d; i++){
      let [a, b, s] = input[idx++].split(' ').map(Number);
      edges[b].push([a, s]);
    }
    const INF = 10_001 * 1_000;
    const visited = Array(n+1).fill(INF);
    
    const pq = new PriorityQueue((a, b) => a[1] > b[1]);
    pq.push([c, 0]);
    visited[c] = 0;
    while(!pq.isEmpty()){
      let [numb, dist] = pq.poll();

      for (let [nextNumb, nextDist] of edges[numb]){
        if (visited[nextNumb] > visited[numb] + nextDist){
          visited[nextNumb] = visited[numb] + nextDist;
          pq.push([nextNumb, visited[nextNumb]]);
        }
      }
    }

    let res = 0;
    let cnt = 0;
    for (let i = 1; i < visited.length; i++){
      if (visited[i] !== INF){
        res = Math.max(res, visited[i]);
        cnt++;
      }
    }
    answer.push([cnt, res]);
  }

  return answer.map((it) => it.join(' ')).join('\n');
}

console.log(solution());
