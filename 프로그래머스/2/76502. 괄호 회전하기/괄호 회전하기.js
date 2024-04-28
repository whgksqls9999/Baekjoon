function solution(s) {
    var answer = 0;
    
     outer: for (let i = 1; i <= s.length; i++){
        const stack = [];
        
        const left = s.slice(0, i);
        const right = s.slice(i);
        const newArr = right.concat(...left);
        
        for (let j = 0; j < newArr.length; j++){
            const cur = newArr[j];
            const top = stack[stack.length - 1];
            
            if(stack.length === 0 || (cur === '(') || (cur === '{') || (cur === '[')){
                stack.push(cur);
            } else {
                if(top.charCodeAt(0) >= cur.charCodeAt(0) - 2 && top.charCodeAt(0) <= cur.charCodeAt(0)){
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }
        }
         
        if(stack.length === 0){
            answer++;
        }
    }
    return answer;
}
