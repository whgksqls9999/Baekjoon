let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let N = parseInt(input[0]);

class Queue {
    constructor() {
        this.storage = {};
        this.front = -1;
        this.rear = -1;
    }

    isEmpty() {
        if (this.rear === this.front) {
            return true;
        } else {
            return false;
        }
    }

    size() {
        return this.rear - this.front;
    }

    push(value) {
        return this.storage[++this.rear] = value;
    }

    peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.storage[this.front + 1];
        }
    }

    poll() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.storage[++this.front];
        }
    }
}

let arr = input[1].split(' ').map((element) =>
    parseInt(element)
)

let visited = new Array(N).fill(1000);

const BFS = function (arr, visited, N) {
    let queue = new Queue();

    visited[0] = 0;
    queue.push([0, arr[0]]);

    while (!queue.isEmpty()) {
        let now = queue.poll();
        let idx = parseInt(now[0]);
        let val = parseInt(now[1]);

        for (let i = 1; i <= val; ++i) {
            if (idx + i < N) {
                if (visited[idx + i] > visited[idx] + 1) {
                    queue.push([idx + i, arr[idx + i]]);
                    visited[idx + i] = visited[idx] + 1;
                }
            }
        }
    }
}

BFS(arr, visited, N);


if (visited[N - 1] === 1000) {
    console.log(-1);
} else {
    console.log(visited[N - 1]);
}