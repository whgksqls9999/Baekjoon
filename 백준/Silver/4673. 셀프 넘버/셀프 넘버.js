const set = new Set();
const answer = [];

for (let i = 0; i <= 10000; ++i){
    let num = 0;
    let numStr = i.toString();

    for (let j = 0; j < numStr.length; ++j){
        num += Number(numStr[j]);
    }

    set.add(i+num);
}

for (let i = 1; i <= 10000; ++i){
    if(set.has(i)) continue;
    answer.push(i);
}

console.log(answer.join('\n'));