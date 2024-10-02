const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

function solution() {
  let idx = 0;
  const [N, M] = input[idx++].split(' ').map(Number);

  const edges = Array(N+1).fill().map(() => []);
  for (let i = 0; i < N; i++){
    const arr = input[idx].split(' ').map(Number).slice(1);
    edges[idx++] = arr;
  }

  const matching = Array(M+1).fill(0);
  for (let i = 1; i <= N; i++){
    const used = Array(M+1).fill(false);
    dfs(i, edges, matching, used);
  }

  return matching.filter((it) => it !== 0).length;
}

function dfs(x, edges, matching, used){
  for (let i of edges[x]){
    if (used[i]) continue;
    used[i] = true;

    if (matching[i] === 0 || dfs(matching[i], edges, matching, used)){
      matching[i] = x;

      return true;
    }
  }
  return false;
}

console.log(solution());
