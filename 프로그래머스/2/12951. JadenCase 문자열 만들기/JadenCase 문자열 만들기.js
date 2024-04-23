function solution(s) {
    let answer = '';
    
    const arr = s.split(' ');
    
    for (word of arr){
        word = word.toLowerCase();
        
        let res = '';
        if (typeof word[0] === 'string'){
            res += word[0].toUpperCase();
            
            if (word.length > 1){
                res += word.slice(1);
            }
 
        }
        
        answer += res + " ";
    }
    
    return answer.slice(0, answer.length - 1);
}