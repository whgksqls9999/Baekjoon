// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

const info = input[0].split(" ").map((element) => Number(element));

let [N, R, Q] = info;

let nodes = Array(N + 1)
  .fill(0)
  .map(() => Array(0));

for (let i = 1; i <= N - 1; ++i) {
  let conn = input[i].split(" ").map((element) => Number(element));

  nodes[conn[0]].push(conn[1]);
  nodes[conn[1]].push(conn[0]);
}

let visited = Array(N + 1).fill(false);
let cnt;
let answer = [];
let dp = Array(N + 1).fill(0);
let depth = Array(N + 1).fill(0);

DFStoDepth(R, 1);

visited = Array(N + 1).fill(false);
DFS(R);

for (let i = 0; i < Q; ++i) {
  let query = Number(input[N + i]);

  answer.push(dp[query]);
}
console.log(answer.join("\n"));

function DFStoDepth(node, depthCnt) {
  visited[node] = true;
  depth[node] = depthCnt;

  nodes[node].forEach((element) => {
    if (!visited[element]) {
      DFStoDepth(element, depthCnt + 1);
    }
  });
}

function DFS(node) {
  visited[node] = true;
  ++cnt;
  if (dp[node] === 0) {
    dp[node]++;
  }

  nodes[node].forEach((element) => {
    if (dp[element] === 0) {
      if (depth[node] < depth[element]) {
        dp[node] += DFS(element);
      }
    } else {
      if (depth[node] < depth[element]) {
        dp[node] += dp[element];
      }
    }
  });

  return dp[node];
}
