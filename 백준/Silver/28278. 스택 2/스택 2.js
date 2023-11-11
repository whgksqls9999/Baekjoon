class Stack{
    constructor(){
        this.arr = [];
    }
    
    push(num){
        this.arr.push(num);
    }
    pop(){
        if (this.isEmpty()){
            return -1;
        } else {
            return this.arr.pop();
        }
    }
    
    size(){
        return this.arr.length;
    }
    
    isEmpty(){
        if (this.size() == 0){
            return 1;
        } else {
            return 0;
        }
    }
    
    peek(){
        if (this.isEmpty()){
            return -1;
        } else {
            return this.arr[this.size()-1];
        }
    }
}

let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let stack = new Stack();

let N = parseInt(input[0]);
let ans = [];

for (let i = 0; i < N; ++i){
    let command = input[i+1].split(' ');
    switch(parseInt(command[0])){
        case 1:
            let num = parseInt(command[1]);
            stack.push(num);
            break;
        case 2:
            ans.push(stack.pop());
            break;
        case 3:
            ans.push(stack.size());
            break;
        case 4:
            ans.push(stack.isEmpty());
            break;
        case 5:
            ans.push(stack.peek());
            break;
    }
}

console.log(ans.join('\n'));

