function solution(citations) {
    var answer = 0;
    
    const arr = Array(10001).fill(0);
    
    for (let i of citations){
        arr[i]++;
    }
    
    let cnt = 0;
    for (let i = 10000; i >= 0; i--){
        if (arr[i] !== 0){
            cnt += arr[i];
            answer = Math.max(answer, Math.min(i, cnt));
        }    
    }

    return answer;
}