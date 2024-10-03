const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

const answer = [];

function solution() {
  let idx = 0;
  const T = Number(input[idx++]);
  const answer = [];
  for (let tc = 0; tc < T; tc++) {
    const [N, M] = input[idx++].split(' ').map(Number);
    const matched = Array(N + 1).fill(0);
    const wants = Array(M + 1).fill().map(() => []);

    for (let i = 0; i < M; i++) {
      const arr = input[idx++].split(' ').map(Number);
      wants[i + 1] = arr;
    }

    for (let i = 1; i <= M; i++) {
      const used = Array(N + 1).fill(false);
      dfs(i, wants, matched, used);
    }

    answer.push(matched.filter((it) => it > 0).length);
  }
  return answer.join('\n');
}

function dfs(x, wants, matched, used) {
  for (let i = wants[x][0]; i <= wants[x][1]; i++) {
    if (used[i]) continue;
    used[i] = true;

    if (matched[i] === 0 || dfs(matched[i], wants, matched, used)) {
      matched[i] = x;
      return true;
    }
  }

  return false;
}

console.log(solution());
