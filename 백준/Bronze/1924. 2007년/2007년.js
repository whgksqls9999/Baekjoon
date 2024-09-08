const input = require('fs').readFileSync(0).toString().trim().split('\n');


const months =[31,28,31,30,31,30,31,31,30,31,30,31];
const days =['SUN', 'MON','TUE','WED','THU','FRI','SAT'];
function solution(){
    let idx = 0;
    const [N, M] = input[idx++].split(' ').map(Number);

    let diff = 0;
    for (let i = 0; i < N - 1; i++){
        diff += months[i];
    }
    diff += M;
    return days[diff % 7];
}


console.log(solution());