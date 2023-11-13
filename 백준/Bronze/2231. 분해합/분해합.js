const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
N = input[0];
NLen = N.length;

console.log(solution(+N, NLen));

function solution(N, NLen){
    let iLen = 0;
    let tmp = 0;
    for (let i = N-9*NLen; i < N; i++){
        iLen = String(i).length;
        tmp = i;
        for(let j = 0; j < iLen; j++){
            tmp += Number(String(i)[j])
        }
        if (tmp == N){
            return i
        }
    }
    return 0;
}