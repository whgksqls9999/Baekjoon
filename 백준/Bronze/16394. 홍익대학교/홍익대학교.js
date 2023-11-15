let input = require('fs').readFileSync('/dev/stdin').toString();

let N = parseInt(input);

console.log(solution(N));

function solution(N){
    return N - 1946;
}