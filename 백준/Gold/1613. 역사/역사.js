const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

function solution() {
  let idx = 0;
  const [n, m] = input[idx++].split(' ').map(Number);
  const arr = Array(n+1).fill().map(() => Array(n+1).fill(0));
  
  for (let i = 0; i < m; i++){
    const [s, e] = input[idx++].split(' ').map(Number);
    arr[s][e] = -1;
    arr[e][s] = 1;
  }

  for (let i = 1; i <= n; i++){
    for (let j = 1; j <= n; j++){
      for (let k = 1; k <= n; k++){
        if (j === k) continue;
        if (arr[j][i] === -1 && arr[i][k] === -1){
          arr[j][k] = -1;
          arr[k][j] = 1;
        } else if (arr[j][i] === 1 && arr[i][k] === 1){
          arr[j][k] = 1;
          arr[k][j] = -1;
        }
      }
    }
  }
  // console.table(arr)

  const answer = [];
  let Q = Number(input[idx++]);
  for (let i = 0; i < Q; i++){
    let [a, b] = input[idx++].split(' ').map(Number);
    answer.push(arr[a][b]);
  }
  return answer.join('\n');
}

console.log(solution());
