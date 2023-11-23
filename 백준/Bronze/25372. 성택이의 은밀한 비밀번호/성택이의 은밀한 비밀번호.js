const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let N = parseInt(input[0]);

let ans = [];

for (let i = 1; i <= N; ++i){
    if(input[i].trim().length >= 6 && input[i].trim().length <= 9){
        ans.push('yes');
    } else {
        ans.push('no');
    }
}

console.log(ans.join('\n'));