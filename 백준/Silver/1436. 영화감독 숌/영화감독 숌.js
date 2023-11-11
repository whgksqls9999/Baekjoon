let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

input = parseInt(input);

let cnt = 0;
let i = 666;
for (i = 666; cnt != input; ++i){
    if(i.toString(10).includes('666')){
        ++cnt;
    }
}
console.log(i-1);