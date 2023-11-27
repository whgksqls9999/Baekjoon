const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

let dr = [-1, 0, 1, 0];
let dc = [0, 1, 0, -1];

let arr = Array(5)
  .fill(0)
  .map((element) => Array(5).fill(0));

for (let i = 0; i < 5; ++i) {
  let row = input[i].split(" ");
  for (let j = 0; j < 5; ++j) {
    arr[i][j] = row[j].trim();
  }
}

let ans = new Set();

for (let i = 0; i < 5; ++i) {
  for (let j = 0; j < 5; ++j) {
    var visited = Array(5)
      .fill(false)
      .map((element) => Array(5).fill(false));
    var num = [];
    DFS(i, j, 0);
  }
}

console.log(ans.size);

function DFS(r, c, cnt) {
  //   visited[r][c] = true;
  num.push(arr[r][c]);

  if (cnt === 5) {
    ans.add(num.join(""));
    return;
  }

  for (let i = 0; i < 4; ++i) {
    let nr = r + dr[i];
    let nc = c + dc[i];
    if (nr >= 0 && nc >= 0 && nr < 5 && nc < 5) {
      //   if (!visited[nr][nc]) {
      DFS(nr, nc, cnt + 1);
      num.pop();
      // visited[nr][nc] = false;
      //   }
    }
  }
}
