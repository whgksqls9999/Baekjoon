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

  const flow = Array(802).fill().map(() => Array(802).fill(0));
  const capacity = Array(802).fill().map(() => Array(802).fill(0));
  const costs = Array(802).fill().map(() => Array(802).fill(0));

  const edges = Array(802).fill().map(() => []);
  for (let i = 0; i < N; i++, idx++){
    const arr = input[idx].split(' ').map(Number);
    for (let j = 0; j < arr[0]; j++){
      const work = arr[j*2+1] + 400;
      const cost = arr[j*2+2];
      edges[idx].push(work);
      edges[work].push(idx);
      edges[work].push(801);
      
      costs[idx][work] = cost;
      costs[work][idx] = -cost;

      capacity[idx][work] = 1;
      capacity[work][801] = 1;
    }
    edges[0].push(idx);
    capacity[0][idx] = 1;
  }

  const INF = 10_000 * 401;
  let works = 0;
  let cost = 0;
  while(true){
    const queue = new Queue();
    const inQueue = Array(802).fill(false);
    const dist = Array(802).fill(INF);
    const parent = Array(802).fill(-1);

    dist[0] = 0;
    queue.push(0);

    while(!queue.isEmpty()){
      let cur = queue.poll();

      inQueue[cur] = false;

      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0){
          if (dist[cur] + costs[cur][next] < dist[next]){
            parent[next] = cur;
            dist[next] = dist[cur] + costs[cur][next];

            if (!inQueue[next]){
              queue.push(next);
              inQueue[next] = true;
            }
          }
        }
      }
    }

    if (parent[801] === -1) break;
    works++;

    for (let t = 801; t !== 0; t = parent[t]){
      flow[parent[t]][t] = 1;
      flow[t][parent[t]] = -1;
    }

    cost += dist[801];
  }

  return `${works}\n${cost}`;
}

console.log(solution());