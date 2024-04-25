function solution(priorities, location) {
    var answer = 0;
    
    const arr = [];
    for (let i = 0; i < priorities.length; i++){
        arr.push({
            prior: priorities[i],
            index: i,
            isProcessed: false,
        })
    }
    priorities.sort();
    
    let curPrior = priorities.length - 1;
    let idx = 0;
    let cnt = 0;
    
    while(curPrior >= 0){
        const cur = arr[idx];
        if (!cur.isProcessed && cur.prior === priorities[curPrior]){
            cur.isProcessed = true;
            curPrior--;
            cnt++;
            
            if (cur.index === location){
                return cnt;
            }
        }
        idx = (idx + 1) % arr.length;
    }
    
    return answer;
}