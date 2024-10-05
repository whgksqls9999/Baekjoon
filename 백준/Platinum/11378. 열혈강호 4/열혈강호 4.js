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
  const [N, M, K] = input[idx++].split(' ').map(Number);

  const capacity = Array(2003).fill().map(() => Array(2003).fill(0));
  const flow = Array(2003).fill().map(() => Array(2003).fill(0));
  const edges = Array(2003).fill().map(() => []);
  
  capacity[0][2002] = K;
  edges[0].push(2002);
  edges[2002].push(0);

  for (let i = 0; i < N; i++, idx++) {
    const [_, ...arr] = input[idx].split(' ').map(Number);

    for (let work of arr) {
      work += 1000;

      // idx - work
      capacity[idx][work] = 1;
      edges[idx].push(work);
      edges[work].push(idx);

      // work - synk
      capacity[work][2001] = 1;
      edges[work].push(2001);
      edges[2001].push(work);
    }

    // source - idx
    capacity[0][idx] = 1;
    edges[0].push(idx);
    edges[idx].push(0);

    capacity[2002][idx] = K;
    edges[2002].push(idx);
    edges[idx].push(2002);
  }

  let answer = 0;
  while (true) {
    const queue = new Queue();
    const pre = Array(2003).fill(-1);
    queue.push(0);
    pre[0] = 0;

    while(!queue.isEmpty() && pre[2001] === -1){
      let cur = queue.poll();
      for (let next of edges[cur]){
        if (capacity[cur][next] - flow[cur][next] > 0 && pre[next] === -1){
          pre[next] = cur;
          queue.push(next);
        }
      }
    }

    if (pre[2001] === -1) break;

    let min_flow = 1;
    // for (let node = 2001; node !== 0; node = pre[node]){
    //   min_flow = Math.min(min_flow, capacity[pre[node]][node] - flow[pre[node]][node]);
    // }
    
    for (let node = 2001; node !== 0; node = pre[node]){
      flow[pre[node]][node] += min_flow;
      flow[node][pre[node]] -= min_flow;
    }
    
    answer += min_flow;
  }

  return answer;
}

console.log(solution());