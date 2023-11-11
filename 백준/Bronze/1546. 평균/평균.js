let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let N = parseInt(input[0]);

let arr = input[1].split(' ');
arr = arr.map((element) => {
    return parseInt(element);
})

let max = 0;
for (let i = 0; i < input[1].length; ++i) {
    if (arr[i] > max) {
        max = arr[i];
    }
}
arr = arr.map((element) =>{
    return (element / max) * 100;
})

let sum = 0;
arr.forEach((element) => {
    sum += element;
})

console.log((sum / N));