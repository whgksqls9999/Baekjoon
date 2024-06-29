function solution(numbers) {
    var answer = [];
    
    let stack = [];
    let maxNum = 0;
    
    for (let i = numbers.length - 1; i >= 0; i--){
        let curNum = numbers[i];
        
        if (curNum >= maxNum){
            maxNum = curNum;
            stack = [curNum];
            answer.push(-1);
        } else {
            for (let j = stack.length; j >= 0; j--){
                let cur = stack[j];
                if (cur > curNum){
                    answer.push(cur);
                    break;
                } else {
                    stack.splice(j, 1);
                }
            }
            stack.push(curNum);
        }
    }
    
    answer.reverse();
    return answer;
}