const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.front = null;
    this.rear = null;
  }

  push(val) {
    const newNode = new Node(val);

    if (!this.rear) {
      this.front = newNode;
      this.rear = newNode;
    } else {
      this.rear.next = newNode;
      this.rear = newNode;
    }
  }

  poll() {
    if (!this.front) return null;

    const value = this.front;
    const returnValue = this.front.value;


    if (value.next) {
      this.front = value.next;
    } else {
      this.front = null;
      this.rear = null;
    }

    return returnValue;
  }

  isEmpty() {
    return this.front === null;
  }
}

function solution() {
  let idx = 0;
  const N = input[idx++];
  const capacity = {};
  const flow = {}
  const edges = {};
  
  const temp = {};
  for (let i = 'A'.codePointAt(0); i <= 'z'.codePointAt(0); i++) {
    temp[i] = 0;
  }

  for (let i = 'A'.codePointAt(0); i <= 'z'.codePointAt(0); i++) {
    capacity[i] = { ...temp };
    flow[i] = { ...temp };
    edges[i] = [];
  }

  for (let i = 0; i < N; i++) {
    const [s, e, d] = input[idx++].split(' ').map((it) => parseInt(it) ? Number(it) : it.codePointAt(0));
    edges[s].push(e);
    edges[e].push(s);
    capacity[s][e] += d;
    capacity[e][s] += d;
  }

  const A = 'A'.codePointAt(0);
  const Z = 'Z'.codePointAt(0);

  let answer = 0;
  while (true) {
    const parent = {};
    for (let i = 'A'.codePointAt(0); i <= 'z'.codePointAt(0); i++) {
      parent[i] = -1;
    }

    const queue = new Queue();
    queue.push(A);
    parent[A] = A;

    while (!queue.isEmpty() && parent[Z] === -1) {
      let cur = queue.poll();

      for (let next of edges[cur]) {
        if (parent[next] === -1 && capacity[cur][next] - flow[cur][next] > 0) {
          queue.push(next);
          parent[next] = cur;
        }
      }
    }

    if (parent[Z] === -1) break;

    let amount = 701 * 1_000;
    for (let p = Z; p !== A; p = parent[p]) {
      amount = Math.min(amount, capacity[parent[p]][p] - flow[parent[p]][p]);
    }

    for (let p = Z; p !== A; p = parent[p]) {
      flow[parent[p]][p] += amount;
      flow[p][parent[p]] -= amount;
    }

    answer += amount;
  }

  return answer;
}

console.log(solution());
