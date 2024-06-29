function solution(topping) {
    var answer = 0;
    
    const left = [];
    
    let set = new Set();
    for (let i of topping){
        if (!set.has(i)){
            set.add(i);
        } 
        left.push(set.size);
    }
    
    set = new Set();
    const right = Array(left.length).fill(0);
    for (let i = topping.length - 1; i >= 0; i--){
        let cur = topping[i];
        
        if (!set.has(cur)){
            set.add(cur);
        }
        right[i] = set.size;
    }
    
    for (let i = 0; i < left.length - 2; i++){
        if (left[i] === right[i + 1]){
            answer++;
        }
    }
        
    return answer;
}