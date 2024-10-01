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
  const n = Number(input[idx++]);
  const m = Number(input[idx++]);
  const INF = 50_001 * 100_000;
  const route = Array(n+1).fill().map(() => Array(n+1).fill([]));
  const arr = Array(n+1).fill().map(() => Array(n+1).fill(INF));
  for (let i = 0; i < m; i++){
    const [s, e, d] = input[idx++].split(' ').map(Number);
    arr[s][e] = Math.min(arr[s][e], d);
    route[s][e] = [s, e];
  }
  
  
  const answer = [];
  for (let i = 1; i <= n; i++){
    for (let j = 1; j <= n; j++){
      for (let k = 1; k <= n; k++){
        if (j === k) {
          route[j][k] = [];
          continue;
        }
        
        let newDist = arr[j][i] + arr[i][k];
        if (newDist < arr[j][k]){
          arr[j][k] = newDist;
          route[j][k] = [...route[j][i], ...(route[i][k].slice(1))];
        }
      }
    }
  }
  
  for (let i = 1; i <= n; i++){
    answer.push(arr[i].slice(1).map((it) => it === INF ? 0 : it).join(' '));
  }

  for (let i = 1; i <= n; i++){
    for (let j = 1; j <= n; j++){
      answer.push(`${route[i][j].length} ${route[i][j].join(' ')}`)
    }
  }
  return answer.join('\n');
}

console.log(solution());
