function solution(msg) {
    var answer = [];
    
    const dictionary = new Dictionary();
    let idx = 27;
    
    
    for (let i = 0; i < msg.length; i++){
        i = Number(i);
        let nextIdx = i + 1;
        let cur = msg[i];
        for (let j = i + 1; j <= msg.length; j++){
            if (dictionary.has(msg.substring(i, j))) {
                cur = msg.substring(i, j);
                nextIdx = j;
                continue;
            };
            break;
        }
        
        let curCode = dictionary.get(cur);
        answer.push(curCode);
        
        if (nextIdx === msg.length) {
            console.log(msg, cur);
            break;
        }
        
        if (!msg[nextIdx]) continue;
        
        let cur_next = cur + msg[nextIdx];
        
        if (dictionary.has(cur_next))continue;
        dictionary.set(cur_next, idx++);
        
        if(cur_next.length > 2){
            i += cur_next.length - 2;
        }
        
    }
    
    console.log(dictionary);
    
    return answer;
}

function Dictionary(){
    const tmp = new Map();
    let idx = 1;
    for (let i = 'A'.charCodeAt(0); i <= 'Z'.charCodeAt(0); i++){
        tmp.set(String.fromCharCode(i), idx++);
    }
    return tmp;
}