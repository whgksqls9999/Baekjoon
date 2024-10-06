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
  const [N, M] = input[idx++].trim().split(' ').map(Number);

  const SOURCE = 401;
  const SYNC = 2;

  const capacity = Array(801).fill().map(() => Array(801).fill(0));
  const flow = Array(801).fill().map(() => Array(801).fill(0));
  const edges = Array(801).fill().map(() => []);

  for (let i = 1; i <= N; i++){
    capacity[i][i+400] = 1;
    edges[i].push(i + 400);
    edges[i + 400].push(i);
  }
  
  for (let i = 0; i < M; i++) {
    const [s, e] = input[idx++].trim().split(' ').map(Number);

    capacity[s + 400][e] = 1;
    edges[s + 400].push(e);
    edges[e].push(s + 400);

    capacity[e + 400][s] = 1;
    edges[e + 400].push(s);
    edges[s].push(e + 400);
  }


  let answer = 0;
  while (true) {
    const queue = new Queue();
    const pre = Array(801).fill(-1);

    queue.push(SOURCE);
    pre[SOURCE] = SOURCE;

    while (!queue.isEmpty() && pre[SYNC] === -1) {
      const cur = queue.poll();
      for (let next of edges[cur]) {
        if (capacity[cur][next] - flow[cur][next] > 0 && pre[next] === -1) {
          queue.push(next);
          pre[next] = cur;
        }
      }
    }

    if (pre[SYNC] === -1) break;

    answer++;

    for (let node = SYNC; node !== SOURCE; node = pre[node]) {
      flow[pre[node]][node]++;
      flow[node][pre[node]]--;
    }

  }
  return answer;
}

console.log(solution());