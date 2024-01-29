const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let answer = 0;

for (let i = 0; i < input.length; i++){
    let cur = input[i].split(' ').map(Number);
    let score = cur.reduce((acc, cur) => acc + cur,0);
    
    answer = Math.max(answer, score);
}

console.log(answer);