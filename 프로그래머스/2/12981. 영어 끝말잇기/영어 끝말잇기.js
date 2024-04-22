function solution(n, words) {
    let answer = [0, 0];

    const set = new Set();
    
    for (let idx = 0; idx < words.length; idx++){
        const preWord = words[idx-1];
        const word = words[idx];

        if(set.has(word)){
            return [idx % n + 1, Math.floor(idx / n) + 1];
        } 
        
        if (preWord && preWord[preWord.length - 1] !== word[0]){
            return [idx % n + 1, Math.floor(idx / n) + 1];
        }         
        
        set.add(word);
    }
    
    return answer;
}