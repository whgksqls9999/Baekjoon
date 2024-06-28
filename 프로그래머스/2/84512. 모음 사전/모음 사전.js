let tmp = new Set();

function solution(word) {
    var answer = 0;
    
    const arr = [-1, 'A', 'E', 'I', 'O', 'U'];
    
    dfs(arr, '', 0, word);
    
    tmp = [...tmp].sort(); 
    
    answer = tmp.indexOf(word) + 1;
    
    return answer;
}

function dfs(arr, cur, depth, word){
    if (depth === 5){    
        if (!cur) return;
        tmp.add(cur);
        return;
    }
    
    for (let i = 0; i < arr.length; i++){
        if (i > 0){
            dfs(arr, cur + arr[i], depth + 1, word);
        } else {
            dfs(arr, cur, depth + 1, word);
        }
    }
}