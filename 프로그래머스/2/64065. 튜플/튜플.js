function solution(s) {
    var answer = [];
    
    const parsedArray = separate(s.substring(1, s.length - 1));
    
    parsedArray.sort((a, b) => a.size - b.size);
    
    const check = new Set();
    
    for (let set of parsedArray){
        for (let i of set){
            if (!check.has(i)){
                check.add(i);
                answer.push(+i);
                break;
            }
        }
    }
    
    return answer;
}

function separate(input){
    const tmp = [];
    
    let isProcessing = false;
    
    let cur = [];
    let curWord = '';
    
    for (let i of input){
        if (i === "{"){
            isProcessing = true;
            cur = [];
            curWord = '';
        } else if (i === '}'){
            isProcessing = false;
            cur.push(curWord);
            tmp.push(new Set(cur));
        } else if (i === ','){
            if (isProcessing){
                cur.push(curWord);
                curWord = '';
            }
        } else {
            curWord += i;
        }
    }
    
    return tmp;
    
}