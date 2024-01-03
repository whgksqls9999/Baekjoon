const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');


(function solution(){
    const N = Number(input[0]);
    console.log(N % 2 === 0 ? 'SK' : 'CY');
}());