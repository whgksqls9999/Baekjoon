const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

//초기 설정
class Queue {
  constructor() {
    this.storage = {};
    this.front = -1;
    this.rear = -1;
  }

  size() {
    return this.rear - this.front;
  }

  isEmpty() {
    return this.size() === 0;
  }

  push(val) {
    return (this.storage[++this.rear] = val);
  }

  peek() {
    if (this.isEmpty()) {
      return null;
    }
    return this.storage[this.front + 1];
  }

  pop() {
    if (this.isEmpty()) {
      return null;
    }
    return this.storage[++this.front];
  }
}

let N = Number(input[0]);

(function solution() {
  const answer = [];
  const arr = [];

  const dataStructures = input[1].split(" ").map(Number);
  const init = input[2].split(" ").map(Number);
  const M = Number(input[3]);
  const datas = input[4].split(" ");

  const queue = new Queue();
  for (let i = N - 1; i >= 0; --i) {
    if (dataStructures[i] === 0) {
      queue.push(init[i]);
    }
  }

  for (let i = 0; i < M; ++i) {
    queue.push(datas[i]);
    answer.push(queue.pop());
  }

  console.log(answer.join(" "));
})();
