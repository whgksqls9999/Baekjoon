const input = require('fs').readFileSync('dev/stdin').toString().trim().split(' ');

if (input[0] * 100 >= input[1]){
    console.log('Yes');
} else {
    console.log('No');
}