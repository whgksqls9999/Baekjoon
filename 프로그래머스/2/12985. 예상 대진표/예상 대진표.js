function solution(n,a,b)
{
    let answer = 0;
    
    let bigger = Math.max(a, b);
    let smaller = Math.min(a, b);
    
    
    while(bigger !== smaller){
        answer++;
        bigger = Math.ceil(bigger / 2);
        smaller = Math.ceil(smaller / 2);
    }

    return Math.floor(answer);
}
