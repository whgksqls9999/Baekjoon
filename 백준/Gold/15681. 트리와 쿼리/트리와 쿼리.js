const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

const info = input[0].split(" ").map((element) => Number(element));

let [N, R, Q] = info;

// 트리 연결 정보 저장하기
let nodes = Array(N + 1)
  .fill(0)
  .map(() => Array(0));
for (let i = 1; i <= N - 1; ++i) {
  let conn = input[i].split(" ").map((element) => Number(element));
  nodes[conn[0]].push(conn[1]);
  nodes[conn[1]].push(conn[0]);
}

// 트리를 이루는 노드의 깊이 구하기
let visited = Array(N + 1).fill(false);
let depth = Array(N + 1).fill(0);
DFStoCalDepth(R, 1);

// 구해진 깊이를 사용해서 각 정점에서의 서브트리 노드 개수 카운트(dp)
let dp = Array(N + 1).fill(0);
let answer = [];
DFS(R);

// 각 쿼리마다 정답 출력하기
for (let i = 0; i < Q; ++i) {
  let query = Number(input[N + i]);
  answer.push(dp[query]);
}
console.log(answer.join("\n"));

// functions
function DFStoCalDepth(node, depthCnt) {
  visited[node] = true;
  depth[node] = depthCnt;

  nodes[node].forEach((element) => {
    if (!visited[element]) {
      DFStoCalDepth(element, depthCnt + 1);
    }
  });
}

function DFS(node) {
  visited[node] = true;
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
