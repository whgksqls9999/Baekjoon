const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

const answer = [];

function solution() {
  let idx = 0;
  const [N, M] = input[idx++].split(' ').map(Number);
  const edges = Array(N+1).fill().map(() => []);

  for (let i = 0; i < N; i++){
    const arr = input[idx].split(' ').slice(1).map(Number);
    edges[idx++] = arr;
  }
  
  const matched = Array(M+1).fill(0);
  
  for (let i = 1; i <= N; i++){
    const used = Array(M+1).fill(false);
    dfs(i, edges, matched, used);
  }

  return matched.filter((it) => it > 0).length;
}

function dfs(x, edges, matched, used) {
  for (let barn of edges[x]){
    if (used[barn]) continue;
    used[barn] = true;

    if (matched[barn] === 0 || dfs(matched[barn],edges,matched,used)){
      matched[barn] = x;
      return true;
    }
  }
  return false;
}


console.log(solution());
