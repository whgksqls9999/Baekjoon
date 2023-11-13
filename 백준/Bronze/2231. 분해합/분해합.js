let input = require('fs').readFileSync('/dev/stdin').toString();

let N = parseInt(input);

let check = false;
let ans = 0;
let j = 1;
let i = 1;
for (; i <= 1000000; ++i, j=i){
    ans = i;
    
    while(j > 0){
        ans += ~~(j % 10);
        j /= 10;
    }

    if (ans === N){
        check = true;
        break;
    }
}

if (check){
    console.log(i);
} else {
    console.log(0);
}