const input = require('fs').readFileSync('dev/stdin').toString();

let N = parseInt(input);

let ans = [];

ans.push(N * 0.78);
ans.push(N*0.8 + N*0.2*0.78);

console.log(ans.join(' '));