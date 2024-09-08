const input = require('fs').readFileSync(0).toString().trim().split('\n');

class PriorityQueue {
    constructor(){
        this.storage = [];
    }

    isEmpty(){
        return this.storage.length === 0;
    }

    deQueue(){
        if (this.isEmpty()) return null;

        const result = this.storage[0];
        const end = this.storage.pop();
        if (!this.isEmpty()) {
            this.storage[0] = end;
            this.heapifyDown();
        }
        return result;
    }

    heapifyDown(){
        let parentIdx = 0;
        const length = this.storage.length;

        while (true) {
            let leftChildIdx = 2 * parentIdx + 1;
            let rightChildIdx = 2 * parentIdx + 2;
            let swapIdx = parentIdx;

            if (leftChildIdx < length && this.storage[leftChildIdx] < this.storage[swapIdx]) {
                swapIdx = leftChildIdx;
            }

            if (rightChildIdx < length && this.storage[rightChildIdx] < this.storage[swapIdx]) {
                swapIdx = rightChildIdx;
            }

            if (swapIdx === parentIdx) break;

            [this.storage[parentIdx], this.storage[swapIdx]] = [this.storage[swapIdx], this.storage[parentIdx]];
            parentIdx = swapIdx;
        }
    }

    heapifyUp(){
        let childIdx = this.storage.length - 1;
        let parentIdx = Math.floor((childIdx - 1) / 2);

        while (childIdx > 0 && this.storage[childIdx] < this.storage[parentIdx]) {
            [this.storage[childIdx], this.storage[parentIdx]] = [this.storage[parentIdx], this.storage[childIdx]];

            childIdx = parentIdx;
            parentIdx = Math.floor((childIdx - 1) / 2);
        }
    }

    enQueue(value){
        this.storage.push(value);
        this.heapifyUp();
    }
}

function solution(){
    let idx = 0;
    const [N, M] = input[idx++].split(' ').map(Number);

    const nodes = Array(N + 1).fill().map(() => []);
    const degrees = Array(N + 1).fill(0);
    for (let i = 0; i < M; i++){
        let [s, e] = input[idx++].split(' ').map(Number);
        nodes[s].push(e);
        degrees[e]++;
    }

    const queue = new PriorityQueue();
    for (let i = 1; i < N + 1; i++){
        if (degrees[i] === 0){
            queue.enQueue(i);
        }
    }
    
    const answer = [];
    while(!queue.isEmpty()){
        let cur = queue.deQueue();
        answer.push(cur);

        for (let next of nodes[cur]){
            degrees[next]--;

            if (degrees[next] === 0){
                queue.enQueue(next);
            }
        }
    }

    return answer.join(' ');
}


console.log(solution());