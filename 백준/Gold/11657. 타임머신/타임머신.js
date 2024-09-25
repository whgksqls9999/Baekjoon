const input = require('fs').readFileSync(0).toString().trim().split('\n');

let idx = 0;
function solution(){
    let [N, M] = input[idx++].split(' ').map(Number)

    const edges = [];
    for (let i = 0; i < M; i++){
        edges.push(input[idx++].split(' ').map(Number));
    }
    const INF = 6000 * 10000
    const visited = Array(N + 1).fill(INF);
    visited[1] = 0;

    for (let i = 0; i < N; i++){
        for (let [s, e, d] of edges){
            if (visited[s] !== INF && visited[s] + d < visited[e]){
                visited[e] = visited[s] + d;

                if (i === N - 1){
                    return -1;
                }
            }
        }
    }

    const answer = [];
    for (let i = 2; i < visited.length; i++){
        answer.push(visited[i] === INF ? -1 : visited[i]);
    }

    return answer.join('\n')
}

console.log(solution())
