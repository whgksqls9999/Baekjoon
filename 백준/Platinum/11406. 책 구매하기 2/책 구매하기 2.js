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

  let SOURCE = 0;
  let SYNC = 201;
  let MAX = 202;
  let INF = 987654321;

  const [N, M] = input[idx++].trim().split(' ').map(Number);

  const capacity = Array(MAX).fill().map(() => Array(MAX).fill(0));
  const flow = Array(MAX).fill().map(() => Array(MAX).fill(0));
  const edges = Array(MAX).fill().map(() => []);

  const customer = input[idx++].split(' ').map(Number);
  for (let i = 0; i < N; i++){
    capacity[SOURCE][i+1] = customer[i];
    edges[SOURCE].push(i+1);
    edges[i+1].push(SOURCE);
  }

  const books = input[idx++].split(' ').map(Number);
  for (let i = 0; i < M; i++){
    capacity[i+101][SYNC] = books[i];
    edges[SYNC].push(i+101);
    edges[i+101].push(SYNC);
  }

  const arr = input.slice(idx).map((it) => it.split(' ').map(Number));
  for (let i = 0; i < M; i++){
    for (let j = 0; j < N; j++){
      capacity[j+1][i+101] = arr[i][j];
      edges[i+101].push(j+1);
      edges[j+1].push(i+101);
    }
  }

  let answer = 0;
  while(true){
    const queue = new Queue();
    const prev = Array(MAX).fill(-1);

    prev[SOURCE] = SOURCE;
    queue.push(SOURCE);
    while(!queue.isEmpty()){
      let cur = queue.poll();

      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0 && prev[next] === -1){
          prev[next] = cur;
          queue.push(next);
        }
      }
    }

    if (prev[SYNC] === -1) break;

    let amount = INF;
    for (let p = SYNC; p !== SOURCE; p = prev[p]){
      amount = Math.min(amount, capacity[prev[p]][p] - flow[prev[p]][p]);
    }

    for (let p = SYNC; p !== SOURCE; p = prev[p]){
      flow[prev[p]][p] += amount;
      flow[p][prev[p]] -= amount;
    }

    answer += amount;
  }

  return answer;
}

console.log(solution());