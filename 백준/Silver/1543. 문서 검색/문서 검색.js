const input = require('fs').readFileSync('dev/stdin').toString().trim().split('\n');

const str = input[0];
const sub = input[1];

console.log(str.split(sub).length - 1);