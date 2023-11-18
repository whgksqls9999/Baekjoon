const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

let N = parseInt(input[0]);

class PriorityQueue {
  constructor() {
    this.heap = [null];
  }

  peek() {
    if (this.isEmpty()) return null;
    return this.heap[1];
  }

  size() {
    return this.heap.length - 1;
  }

  isEmpty() {
    return this.size() === 0;
  }

  push(val) {
    let current = this.heap.length;

    while (current > 1) {
      let parent = ~~(current / 2);

      if (this.heap[parent] > val) {
        this.heap[current] = this.heap[parent];
        current = parent;
      } else break;
    }
    this.heap[current] = val;
  }

  poll() {
    if (this.isEmpty()) return 0;

    let item = this.heap[1];
    this.heap[1] = this.heap[this.size()];
    this.heap.splice(this.size());

    let p = 1;
    let ch = p * 2;

    if (ch + 1 <= this.size() && this.heap[ch] > this.heap[ch + 1]) {
      ch += 1;
    }

    while (ch <= this.size() && this.heap[p] > this.heap[ch]) {
      let tmp = this.heap[ch];
      this.heap[ch] = this.heap[p];
      this.heap[p] = tmp;

      p = ch;
      ch = p * 2;
      if (ch + 1 <= this.size() && this.heap[ch] > this.heap[ch + 1]) {
        ch += 1;
      }
    }

    return item;
  }
}

console.log(solution());

function solution() {
  let pq = new PriorityQueue();
  let ans = "";

  for (let i = 0; i < N; ++i) {
    let inp = parseInt(input[i + 1]);

    if (inp === 0) {
      ans += pq.poll() + "\n";
    } else {
      pq.push(inp);
    }
  }
  return ans;
}