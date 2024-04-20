function solution(brown, yellow) {
    let answer = [];
    
    const size = [];
    
    let total = brown + yellow;
    for (let i = 1; i <= total; i++){
        if (i < total/i) continue;
        if (total % i === 0){
            size.push([i, total / i]);
        }
    }
    
    
    for (let arr of size){
        let [height, width] =[arr[0], arr[1]];
        console.log(arr);
        if (height * 2 + (width - 2) * 2 === brown){
            answer = arr;
            break;
        }
    }
    return answer;
}