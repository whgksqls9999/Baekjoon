let input = require('fs').readFileSync('/dev/stdin').toString();

let N = input;

console.log(solution(N));

function solution(N){
    let length = N.length;

    let iLen = 0;
    let tmp = 0;
    for (let i = N-9*N.length; i <= 1000000; ++i){
        iLen = String(i).length;
        tmp = i;
        for (let j = 0; j < iLen; ++j){
            tmp += Number(String(i)[j]);
        }

        if (tmp == N){
            return i;
        }
    }
    return 0;
}