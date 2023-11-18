let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let N = parseInt(input[0]);

console.log(solution(N));

function solution(N) {
    let ans = new Array(N);
    for (let i = 1; i <= N; ++i) {
        ans[i - 1] = new Array(4).fill(0);

        let M = parseInt(input[i]);

        if (M >= 25) {
            ans[i - 1][0] += ~~(M / 25);
            M = M % 25;
        }
        if (M >= 10) {
            ans[i - 1][1] += ~~(M / 10);
            M %= 10;
        }
        if (M >= 5) {
            ans[i - 1][2] += ~~(M / 5);
            M %= 5;
        }

        ans[i - 1][3] += ~~(M / 1);
    }

    let answer = '';
    for (let i = 0; i < N; ++i){
        answer += ans[i].join(' ') + '\n';
    }
    return answer;
}   