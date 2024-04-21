function solution(arr) {    
    let max = Math.max(...arr);
    let mod = max;
    
    while(!arr.every((it) => mod % it === 0)){
        mod += max;
    }
    
    return mod;
}