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
  let [N, S, E, M] = input[idx++].split(' ').map(Number);

  const edges = Array(N).fill().map(() => []);
  for (let i = 0; i < M; i++) {
    const [s, e, d] = input[idx++].split(' ').map(Number);
    edges[s].push([e, -d]);
  }

  const cost = input[idx++].split(' ').map(Number);
  const INFP = -1_000_000 * 51;
  const INFM = 1_000_000 * 51;
  const visitedP = Array(N).fill(INFP);
  const visitedM = Array(N).fill(INFM);
  visitedP[S] = cost[S];
  visitedM[S] = cost[S];

  let isInfinite = 0;
  let isMinusInfinite = 0;
  let originResult = INFP;
  for (let i = 1; i <= N; i++) {
    for (let curEdge = 0; curEdge < edges.length; curEdge++) {
      for (let [e, d] of edges[curEdge]) {
        if (visitedP[curEdge] !== INFP && visitedP[curEdge] + d + cost[e] > visitedP[e]) {
          visitedP[e] = visitedP[curEdge] + d + cost[e];

          if (i !== N && e === E) {
            originResult = Math.max(visitedP[e], originResult);
          }
          if (i === N && visitedP[E] !== INFP) {
            // BFS 첵으로 E에 도달 가능한지 체크 후 true
            if (BFS(e, edges, E, N)) {
              isInfinite = visitedP[e] - originResult;
            }
          }
        }
        // if (visitedM[curEdge] !== INFP && visitedM[curEdge] + d + cost[e] < visitedM[e]) {
        //   let prev = visitedM[e];
        //   visitedM[e] = visitedM[curEdge] + d + cost[e];

        //   if (i === N && visitedM[E] !== INFM) {
        //     if (BFS(e, edges, E, N)) {
        //       isMinusInfinite = prev - visitedM[e];
        //     }
        //   }
        // }
      }
    }
  }
  

  if (visitedP[E] === INFP) return "gg";
  if (Math.abs(isInfinite) > Math.abs(isMinusInfinite)) return "Gee";
  if (S === E) return visitedP[S];
  return originResult;
}

function BFS(e, nodes, des, N) {
  const visited = Array(N).fill(false);
  const queue = new Queue()
  queue.push(e);
  visited[e] = true;
  
  while (!queue.isEmpty()) {
    let cur = queue.poll();
    
    if (cur === des) return true;

    for (let [E, D] of nodes[cur]) {
      if (!visited[E]) {
        queue.push(E);
        visited[E] = true;
      }
    }
  }

  return false;
}

console.log(solution());
