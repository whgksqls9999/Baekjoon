function solution(s) {
    let answer = [];
    
    let num = s;
    let zeroCnt = 0;
    let transCnt = 0;
    
    while(num != 1){
        const [newNum, newCnt] = trans(num);
        num = newNum;
        zeroCnt += newCnt;
        transCnt += 1;
    }
    
    answer = [transCnt, zeroCnt];
    
    return answer;
}

function trans(num) {
    let returnVal = "";
    let zeroCnt = 0;

    for (let char of num) {
        if (char !== "0") {
            returnVal += char;
        } else {
            zeroCnt++;
        }
    }

    returnVal = returnVal.length.toString(2);

    return [returnVal, zeroCnt];
}