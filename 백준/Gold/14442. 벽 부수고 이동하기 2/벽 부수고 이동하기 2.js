const input = require('fs').readFileSync(0).toString().trim().split('\n');

class Node {
    constructor(value){
      this.cur = value;
      this.next = null;
    }
  }
   
  class Queue {
    constructor(){
      this.front = null;
      this.rear =null;
      this.length = 0;
    }
    push(value){
      const node = new Node(value);
      if(this.length === 0){
        this.front = node;
      }else {
        this.rear.next = node;
      }
      this.rear = node;
      this.length++
    }
    poll(){
      if(this.length === 0) return false;
      const returnValue = this.front.cur
      this.front = this.front.next;
      this.length--;
      return returnValue;
    }
  }
let idx = 0;
let [N, M, K] = input[idx++].split(' ').map(Number);

let map = Array(N).fill().map(() => input[idx++].trim().split('').map(Number));

const visited = Array(N).fill().map(() => Array(M).fill().map(() => Array(K+1).fill(-1)));

const queue = new Queue();
queue.push([0, 0, K]);

visited[0][0][K] = 1;
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

while(queue.length > 0){
    let cur = queue.poll();

    if (cur[0] === N-1 && cur[1] === M-1) break;

    for (let i = 0; i < 4; i++){
        let nr = cur[0] + dr[i];
        let nc = cur[1] + dc[i];
        
        if (nr >= 0 && nr < N && nc >= 0 && nc < M){
            if (map[nr][nc] === 0){
                if (visited[nr][nc][cur[2]] !== -1) continue;
                visited[nr][nc][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
                queue.push([nr, nc, cur[2]]);
            } else if (map[nr][nc] === 1){
                if (cur[2] === 0) continue;
                if (visited[nr][nc][cur[2] - 1] !== -1) continue;
                visited[nr][nc][cur[2] - 1] = visited[cur[0]][cur[1]][cur[2]] + 1;
                queue.push([nr, nc, cur[2] - 1]);
            }
        }
    }

}

let answer = Number.MAX_SAFE_INTEGER;
for (let i = 0; i < K+1; i++){
    if (visited[N-1][M-1][i] !== -1){
        answer = Math.min(answer, visited[N-1][M-1][i]);
    }
}

console.log(answer === Number.MAX_SAFE_INTEGER ? -1 : answer);
