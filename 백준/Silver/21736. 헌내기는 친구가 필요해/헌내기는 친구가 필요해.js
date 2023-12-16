const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

const [N, M] = input[0].split(" ").map((element) => Number(element));
const arr = Array(N)
  .fill(0)
  .map((e, idx) => input[1 + idx].trim().split(""));
const visited = Array(N)
  .fill(0)
  .map(() => Array(M).fill(false));

let answer = 0;

solution();

function solution() {
  for (let i = 0; i < N; ++i) {
    for (let j = 0; j < M; ++j) {
      if (arr[i][j] === "I") {
        DFS(i, j);
        console.log(answer ? answer : "TT");
        return;
      }
    }
  }
}

function DFS(r, c) {
  visited[r][c] = true;

  if (arr[r][c] === "P") {
    ++answer;
  }

  for (let i = 0; i < 4; ++i) {
    let nr = r + dr[i];
    let nc = c + dc[i];
    if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
      if (!visited[nr][nc] && arr[nr][nc] !== "X") {
        DFS(nr, nc);
      }
    }
  }
}
