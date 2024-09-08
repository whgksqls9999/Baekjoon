const input = require('fs').readFileSync(0).toString().trim().split('\n');

class PriorityQueue {
    constructor(){
        this.storage = [null];
    }

    isEmpty(){
        return this.storage.length === 1;
    }

    deQueue(){
        if (this.isEmpty()) return null;
        
        const result = this.storage[1];
        const end = this.storage.pop();
        if (!this.isEmpty()){
            this.storage[1] = end;
            this.heapifyDown();
        }
        return result;
    }

    heapifyDown(){
        let parentIdx = 1;
        let childIdx = parentIdx * 2;
        let length = this.storage.length;
        if (childIdx + 1 < length && this.storage[childIdx + 1] < this.storage[childIdx]){
            childIdx++;
        }
        
        while(childIdx < length && this.storage[parentIdx] > this.storage[childIdx]){

            [this.storage[parentIdx], this.storage[childIdx]] = [this.storage[childIdx], this.storage[parentIdx]]

            parentIdx = childIdx;
            childIdx = parentIdx * 2;

            if (childIdx + 1 < length && this.storage[childIdx + 1] < this.storage[childIdx]){
                childIdx++;
            }
        }
    }

    heapifyUp(){
        let length = this.storage.length;
        let childIdx = length - 1;
        let parentIdx = Math.floor(childIdx / 2);

        while(parentIdx > 0 && this.storage[parentIdx] > this.storage[childIdx]){
            [this.storage[parentIdx], this.storage[childIdx]] = [this.storage[childIdx], this.storage[parentIdx]]

            childIdx = parentIdx;
            parentIdx = Math.floor(childIdx / 2);
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