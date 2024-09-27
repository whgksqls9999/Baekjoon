const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

class Queue {
  constructor() {
    this.storage = {};
    this.front = -1;
    this.rear = -1;
  }

  isEmpty() {
    return this.front === this.rear;
  }

  push(val) {
    this.storage[++this.rear] = val;
  }

  poll() {
    if (this.isEmpty()) return null;
    const result = this.storage[this.front + 1];
    delete this.storage[++this.front];
    return result;
  }
}

function calDist(a, b) {
  return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
}

function solution() {
  let idx = 0;
  let T = Number(input[idx++]);
  const answer = [];

  for (let i = 0; i < T; i++) {
    let n = Number(input[idx++]);

    const pos = [];
    for (let j = 0; j < n + 2; j++) {
      pos.push(input[idx++].split(' ').map(Number));
    }

    const nodes = Array(n + 3)
      .fill()
      .map(() => []);
    for (let j = 0; j < pos.length; j++) {
      for (let k = j + 1; k < pos.length; k++) {
        if (calDist(pos[j], pos[k]) <= 1000) {
          nodes[j].push([k, ...pos[k]]);
          nodes[k].push([j, ...pos[j]]);
        }
      }
    }

    const visited = Array(n + 3).fill(false);
    const queue = new Queue();
    queue.push([0, ...pos[0]]);
    visited[0] = true;
    let check = false;
    
    while (!queue.isEmpty()) {
      let cur = queue.poll();

      if (cur[0] === n + 1) {
        check = true;
        break;
      }
      for (let next of nodes[cur[0]]) {
        if (!visited[next[0]]) {
          queue.push(next);
          visited[next[0]] = true;
        }
      }
    }
    answer.push(check ? 'happy' : 'sad');
  }
  return answer.join('\n');
}

console.log(solution());
