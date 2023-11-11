let input = require('fs').readFileSync('/dev/stdin').toString().split(' ');

let [a,b] = [parseInt(input[0]), parseInt(input[1])];

console.log((a+b)*(a-b));