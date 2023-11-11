let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let ans = new Array(42).fill(0);

for (let i = 0; i < 10; ++i){
    ans[parseInt(input[i]) % 42]++;
}

let cnt = 0;
for (let i = 0; i < ans.length; ++i){
    if(ans[i] > 0){
        ++cnt;
    }
}

console.log(cnt);