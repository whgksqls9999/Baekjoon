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
  for (let i = 1; i <= N; i++){
    capacity[SOURCE][i] = customer[i-1];
    edges[SOURCE].push(i);
    edges[i].push(SOURCE);
  }

  const books = input[idx++].split(' ').map(Number);
  for (let i = 1; i <= M; i++){
    capacity[i+100][SYNC] = books[i-1];
    edges[i+100].push(SYNC);
    edges[SYNC].push(i+100);
  }

  const cost = Array(MAX).fill().map(()=> Array(MAX).fill(0));
  const costInput = [];

  for (let i = 0; i < M; i ++){
    const arr = input[idx++].split(' ').map(Number);
    costInput.push(arr);
  }
  
  for (let i = 0; i < M; i++){
    for (let j = 0; j < N; j++){
      capacity[j+1][i+100+1] = INF;
      edges[j+1].push(i+100+1);
      edges[i+100+1].push(j+1);
      cost[j+1][i+100+1] = costInput[i][j];
      cost[i+100+1][j+1] = -costInput[i][j];
    }
  }

  let answer = 0;

  while(true){
    const queue = new Queue();
    const inQueue = Array(MAX).fill(false);
    const dist = Array(MAX).fill(INF);
    const prev = Array(MAX).fill(-1);

    queue.push(SOURCE);
    prev[SOURCE] = SOURCE;
    dist[SOURCE] = 0;
    
    while(!queue.isEmpty()){
      let cur = queue.poll();
      inQueue[cur] = false;

      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0 && dist[cur] + cost[cur][next] < dist[next]){
          dist[next] = dist[cur] + cost[cur][next];
          prev[next] = cur;

          if (!inQueue[next]){
            queue.push(next);
            inQueue[next] = true;
          }
        }
      }
    }

    if (prev[SYNC] === -1) break;

    let amount = INF;
    for (let node = SYNC; node !== SOURCE; node = prev[node]){
      amount = Math.min(amount, capacity[prev[node]][node] - flow[prev[node]][node]);
    }

    
    for (let node = SYNC; node !== SOURCE; node = prev[node]){
      answer += amount * cost[prev[node]][node];
      flow[prev[node]][node] += amount;
      flow[node][prev[node]] -= amount;
    }
  }

  return answer;
}

console.log(solution());