function solution(n, t, m, p) {
    var answer = '';
    
    for (let i = 0, order = 1; true; i++){
        let curNum = i.toString(n);
        if (typeof curNum === 'string'){
            curNum = curNum.toUpperCase();
        }
                
        for (let char of curNum){
            if (order === p) answer += char;
            
            if (answer.length === t) return answer;
            
            order++;
            if (order > m) order = 1;
        }
    }
}