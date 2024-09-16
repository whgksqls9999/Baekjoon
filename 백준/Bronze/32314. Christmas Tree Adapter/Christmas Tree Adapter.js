const input = require('fs').readFileSync(0).toString().trim().split('\n');

const a = Number(input[0]);
const [b, c] = input[1].split(' ').map(Number);

console.log((b / c) >= a ? 1 : 0);