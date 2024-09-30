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
  let N = Number(input[idx++]);
  let arr = Array(N).fill().map(() => input[idx++].trim().split('').map(Number));

  const visited = Array(N).fill().map(() => Array(N).fill(2501))
  const pq = new PriorityQueue((a, b) => a[2] > b[2]);
  
  pq.push([0, 0, 0]);
  visited[0][0] = 0;
  while (!pq.isEmpty()) {
    let [r, c, d] = pq.poll();
    
    for (let i = 0; i < 4; i++) {
      let nr = r + dr[i];
      let nc = c + dc[i];

      if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
        if (visited[nr][nc] === 2501) {
          if (arr[nr][nc] === 1) {
            visited[nr][nc] = visited[r][c];
            pq.push([nr, nc, visited[r][c]]);
          } else {
            visited[nr][nc] = visited[r][c] + 1;
            pq.push([nr, nc, visited[r][c] + 1]);
          }
        }
      }
    }
  }
  
  return visited[N-1][N-1];
}

console.log(solution());
