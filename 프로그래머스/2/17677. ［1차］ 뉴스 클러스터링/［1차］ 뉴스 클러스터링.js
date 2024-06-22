function solution(str1, str2) {
    var answer = 0;
    
    const [set, arr] = tokenizingWord(str1, str2);
    
    answer = arr !== 0 ? Math.floor(set / arr * 65536) : 65536;
    
    return answer;
}

function tokenizingWord(str1, str2){
    const tmp1 = {};
    const tmp2 = {};
    
    for (let i = 0; i < str1.length - 1; i++){
        const word = str1.slice(i, i+2).toLowerCase();
    
        if (!(/^[a-zA-Z]{2}$/.test(word))) continue;
        tmp1[word] = (tmp1[word] ?? 0) + 1;    
    }
    
    for (let i = 0; i < str2.length - 1; i++){
        const word = str2.slice(i, i+2).toLowerCase();
        
        if (!(/^[a-zA-Z]{2}$/.test(word))) continue;
        tmp2[word] = (tmp2[word] ?? 0) + 1;
    }
            
    let cnt = 0, union = 0;

    for (let key in tmp1){
        if (tmp2[key]){
            cnt += Math.min(tmp1[key], tmp2[key]);
            union += Math.max(tmp1[key], tmp2[key]);
        } else {
            union += tmp1[key];
        }
    }
    
    for (let key in tmp2){
        if (!tmp1[key]){
            union += tmp2[key];
        }
    }
    
    
    return [cnt, union];
}