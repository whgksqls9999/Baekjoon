function solution(k, tangerine) {
    let answer = 0;
    
    const map = new Map();
    for (const i of tangerine){
        if (map.has(i)){
            let cnt = map.get(i);
            map.set(i, cnt+1);
        } else {
            map.set(i, 1);
        }
    }
    answer = map.size;
    
    const arr = [...map];
    arr.sort((a,b) => a[1] - b[1]);
    let cnt = tangerine.length - k;
    
    for (let i = 0; i < arr.length; i++){
        if (cnt >= arr[i][1]){
            cnt = cnt - arr[i][1];
            answer--;
        }
    }
    return answer;
}