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
  const [N, M] = input[idx++].split(' ').map(Number);
  const capacity = Array(N + 1).fill().map(() => Array(N + 1).fill(0));
  const flow = Array(N + 1).fill().map(() => Array(N + 1).fill(0));
  const edges = Array(N + 1).fill().map(() => []);
  for (let i = 0; i < M; i++) {
    const [s, e] = input[idx++].split(' ').map(Number);
    edges[s].push(e);
    edges[e].push(s);
    capacity[s][e] = 1;
  }

  let answer = 0;
  while (true) {
    const parent = Array(N + 1).fill(-1);
    parent[1] = 1;

    const queue = new Queue();
    queue.push(1);
    
    while(!queue.isEmpty() && parent[2] === -1){
      let cur = queue.poll();

      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0 && parent[next] === -1){
          queue.push(next);
          parent[next] = cur;
        }
      }
    }

    if (parent[2] === -1) break;
    answer++;
    let amount = 1;
    for (let i = 2; i !== 1; i = parent[i]){
      amount = Math.min(amount, capacity[parent[i]][i] - flow[parent[i]][i]);
    }

    for (let i = 2; i !== 1; i = parent[i]){
      flow[parent[i]][i] += amount;
      flow[i][parent[i]] -= amount;
    }
  }

  return answer;
}

console.log(solution());
