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

  const capacity = Array(802).fill().map(() => Array(802).fill(0));
  const flow = Array(802).fill().map(() => Array(802).fill(0));
  const cost = Array(802).fill().map(() => Array(802).fill(0));
  const edges = Array(802).fill().map(() => []);

  for (let i = 0; i < N; i++, idx++) {
    const [_, ...arr] = input[idx].split(' ').map(Number);
    for (let j = 0; j < arr.length; j += 2) {
      cost[idx][arr[j] + 400] = -arr[j + 1];
      cost[arr[j] + 400][idx] = arr[j + 1];

      edges[idx].push(arr[j] + 400);
      edges[arr[j] + 400].push(idx);
      edges[arr[j] + 400].push(801);

      capacity[idx][arr[j] + 400] = 1;
      capacity[arr[j] + 400][801] = 1;
    }
    edges[0].push(idx);
    capacity[0][idx] = 1;
  }

  let cnt = 0;
  let total_cost = 0;
  const INF = 10_000 * 401;

  while (true) {
    const queue = new Queue();
    const inQueue = Array(802).fill(false);
    const visited = Array(802).fill(INF);
    const pre = Array(802).fill(-1);

    visited[0] = 0;
    queue.push(0);

    while (!queue.isEmpty()) {
      let cur = queue.poll();
      inQueue[cur] = false;
      
      for (let next of edges[cur]) {
        if (capacity[cur][next] - flow[cur][next] > 0 && visited[next] > visited[cur] + cost[cur][next]) {
          visited[next] = visited[cur] + cost[cur][next];
          pre[next] = cur;

          if (!inQueue[next]) {
            queue.push(next);
            inQueue[next] = true;
          }
        }
      }
    }

    if (pre[801] === -1) break;

    cnt++;
    for (let p = 801; p !== 0; p = pre[p]) {
      flow[pre[p]][p] = 1;
      flow[p][pre[p]] = -1;
    }

    total_cost += visited[801];
  }
  return `${cnt}\n${-total_cost}`;
}

console.log(solution());