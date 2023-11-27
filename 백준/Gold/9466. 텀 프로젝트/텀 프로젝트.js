const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

let T = Number(input[0]);

let answer = [];
for (let t = 1; t <= T; ++t) {
  let N = Number(input[2 * t - 1]);
  var nums = input[2 * t].split(" ").map((element) => Number(element));

  var cnt = N;
  var teamCheck = Array(N + 1);
  var visited = Array(N + 1);
  for (let i = 1; i <= N; ++i) {
    if (!visited[i]) {
      DFS(i);
    }
  }
  answer.push(cnt);
}

console.log(answer.join("\n"));

function DFS(curr) {
  visited[curr] = true;

  let next = nums[curr - 1];
  if (!visited[next]) {
    DFS(next, visited, teamCheck, nums);
  } else {
    if (!teamCheck[next]) {
      --cnt;
      while (next != curr) {
        next = nums[next - 1];
        --cnt;
      }
    }
  }
  teamCheck[curr] = true;
}
