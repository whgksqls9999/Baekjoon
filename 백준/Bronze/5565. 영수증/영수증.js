const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let N = parseInt(input[0]);

for (let i = 0; i < 9; ++i){
    N -= parseInt(input[i+1]);
}

console.log(N);