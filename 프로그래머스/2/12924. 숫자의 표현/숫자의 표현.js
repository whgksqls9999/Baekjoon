function solution(n) {
    let answer = 0;
    
    let lo = 0;
    let hi = 1;
    let sum = hi;
    
    while(hi <= Math.ceil(n/2)){
        if (sum < n){
            hi++;
            sum += hi;
        } else if (sum >= n){
            sum -= lo;
            lo++;
            if (sum === n){
                answer++;
            }
        }
    }
    
    if(n !== 1){
        answer++;
    }

    
    return answer;
}