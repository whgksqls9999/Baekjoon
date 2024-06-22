let visited;
let max = 0;

function solution(k, dungeons) {
    var answer = -1;
    
    visited = Array(dungeons.length).fill(false);
    
    dfs(k, 0, dungeons);
    
    answer = max;
    return answer;
}

function dfs(hp, depth, dungeons){
    if (depth > max){
        max = depth;
    }
    
    for (let i = 0; i < dungeons.length; i++){
        if (visited[i]) continue;
        
        let [need, consumption] = dungeons[i];
        
        if (hp >= need){
            visited[i] = true;
            dfs(hp - consumption, depth + 1, dungeons);
            visited[i] = false;
        }
    }
}