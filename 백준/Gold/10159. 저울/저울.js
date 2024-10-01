const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

function solution() {
  let idx = 0;
  let N = Number(input[idx++]);
  let M = Number(input[idx++]);
  let arr = Array(N+1).fill().map(() => Array(N+1).fill(false));

  for (let i = 0; i < M; i++){
    let [s, e] = input[idx++].split(' ').map(Number);
    arr[s][e] = true;
  }

  for (let i = 1; i <= N; i++){
    for (let j = 1; j <= N; j++){
      for (let k = 1; k <= N; k++){
        if (j === k) continue;
        if (arr[j][i] && arr[i][k]){
          arr[j][k] = true;
        }
      }
    }
  }
  
  const answer = Array(N).fill(0);
  for (let i = 1; i <= N; i++){
    for (let j = 1; j<= N; j++){
      if (i === j) continue;
      if (arr[i][j]){
        arr[j][i] = true;
      }
    }
  }
  for (let i = 1; i <= N; i++){
    for (let j = 1; j<= N; j++){
      if (i === j) continue;
      if (!arr[i][j]){
        answer[i-1]++;
      }
    }
  }

  return answer.join('\n');
}

console.log(solution());
