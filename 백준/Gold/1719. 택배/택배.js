const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

function solution() {
  let idx = 0;
  const [N, M] = input[idx++].split(' ').map(Number);
  const INF = 10_001 * 1000;
  const arr = Array(N+1).fill().map(() => Array(N+1).fill(INF));
  const route = Array(N+1).fill().map(() => Array(N+1).fill([]));
  
  for (let i = 0; i < M; i++){
    const [s, e, d] = input[idx++].split(' ').map(Number);
    arr[s][e] = d;
    arr[e][s] = d;
    route[s][e] = [e];
    route[e][s] = [s];
  }
  
  for (let i = 1; i <= N; i++){
    for (let j = 1; j <= N; j++){
      for (let k = 1; k <= N; k++){
        if (j === k) continue;

        if (arr[j][i] + arr[i][k] < arr[j][k]){
          arr[j][k] = arr[j][i] + arr[i][k];
          // arr[k][j] = arr[k][i] + arr[i][j];
          route[j][k] = [...route[j][i], ...(route[i][k].slice(1))];
          // route[k][j] = [...route[j][i], ...(route[i][k].slice(0, route[i][k].length-1))];
        }
      }
    }
  }
  
  const answer = Array(N).fill().map(() => []);
  for (let i = 1; i <= N; i++){
    for (let j = 1; j <= N; j++){
      if (i === j) {
        answer[i-1].push('-');
        continue;
      }
      answer[i-1].push(...route[i][j]);
    }
  }

  return answer.map((it) => it.join(' ')).join('\n');
}

console.log(solution());
