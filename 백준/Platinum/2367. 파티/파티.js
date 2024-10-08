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
  const [N, K, D] = input[idx++].split(' ').map(Number);

  const SOURCE = 0;
  const SYNC = N+D+1;

  const capacity = Array(N+D+2).fill().map(() => Array(N+D+2).fill(0));
  const flow = Array(N+D+2).fill().map(() => Array(N+D+2).fill(0));
  const edges = Array(N+D+2).fill().map(() => []);

  const arr = input[idx++].split(' ').map(Number);

  for (let i = 0; i < D; i++){
    capacity[N+i+1][SYNC] = arr[i];
    edges[N+i+1].push(SYNC);
    edges[SYNC].push(N+i+1);
  }

  for (let j = 0; j < N; j++){
    capacity[SOURCE][j+1] = K;
    edges[SOURCE].push(j+1);
    edges[j+1].push(SOURCE);
  }

  for (let i = 0; i < N; i++){
    const [_, ...people] = input[idx++].split(' ').map(Number);

    for (let food of people){
      capacity[i+1][food + N] = 1;
      edges[i+1].push(food+N);
      edges[food+N].push(i+1);
    }
  }

  let answer = 0;
  while(true){
    const queue = new Queue();
    const pre = Array(N+D+2).fill(-1);

    queue.push(SOURCE);
    while(!queue.isEmpty()){
      let cur = queue.poll();
      
      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0 && pre[next] === -1){
          pre[next] = cur;
          queue.push(next);
        }
      }
    }

    if (pre[SYNC] === -1) break;

    let amount = Number.MAX_SAFE_INTEGER;
    for (let p = SYNC; p != SOURCE; p = pre[p]){
      amount = Math.min(amount, capacity[pre[p]][p] - flow[pre[p]][p]);
    }

    for (let p = SYNC; p != SOURCE; p = pre[p]){
      flow[pre[p]][p] += amount;
      flow[p][pre[p]] -= amount;
    }

    answer += amount;
  }
  return answer;
}

console.log(solution());