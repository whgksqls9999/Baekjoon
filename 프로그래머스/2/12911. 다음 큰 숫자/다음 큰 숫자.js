function solution(n) {
    let answer = 0;
    
    let binaryNum = n.toString(2);
    let cnt = 0;
    
    for (let i of binaryNum){
        if (i === '1'){
            cnt++;
        }
    }
    
    let cnt1 = 0;
    for (let i = n + 1; true; i++){
        let binaryI = i.toString(2);
        for (let j of binaryI){
            if (j === '1'){
                cnt1++;
            }
        }
        
        if (cnt === cnt1){
            answer = i;
            break;
        }
        
        cnt1 = 0;
    }

    return answer;
}