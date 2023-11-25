let arr = Array(10001).fill(0);
let check = Array(10001).fill(false);
for (let i = 1; i <= 9972; ++i){
    arr[i] = i;
    
    let num = i;
    let stringI = i.toString().split('');
    stringI.forEach((element) => {
        num += Number(element);
    })

    if(num >= 10001) continue;
    check[num] = true;
}

let answer = '';

for (let i = 1; i <= 10000; ++i){
    if (check[i]) continue;
    answer += i+"\n";
}

console.log(answer);