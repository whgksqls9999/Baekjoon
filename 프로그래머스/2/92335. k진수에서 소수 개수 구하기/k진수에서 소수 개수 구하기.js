function solution(n, k) {
    var answer = 0;
    
    let str = n.toString(k).split('0').filter((it) => it !== '').map((it) => Number(it));

    for (let num of str){
        if (decimalCheck(num)){
            answer++;
        }
    }
    return answer;
}

function decimalCheck(num){
    if (num === 1) return false;
    
    for (let i = 2; i <= Math.sqrt(num); i++){
        if (num % i === 0){
            return false;
        }
    }
    return true;
}