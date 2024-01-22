const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, Q] = input[0].split(" ").map(Number);

const tree = Array(N * 4).fill(BigInt(0));

function solution() {
  const answer = [];

  for (let i = 0; i < Q; i++) {
    const [type, p, q] = input[i + 1].split(" ").map(Number);

    if (type === 1) {
      sum(1, 1, N, p, q);
    } else {
      answer.push(query(1, 1, N, p, q));
    }
  }

  console.log(answer.join("\n"));
}

solution();

function sum(node, start, end, idx, val) {
  if (idx < start || end < idx) {
    return;
  }

  if (start === end) {
    tree[node] += BigInt(val);
    return;
  }

  let mid = Math.floor((start + end) / 2);
  sum(node * 2, start, mid, idx, val);
  sum(node * 2 + 1, mid + 1, end, idx, val);
  tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

function query(node, start, end, left, right) {
  if (end < left || right < start) {
    return BigInt(0);
  }

  if (left <= start && end <= right) {
    return tree[node];
  }

  let mid = Math.floor((start + end) / 2);
  let l = query(node * 2, start, mid, left, right);
  let r = query(node * 2 + 1, mid + 1, end, left, right);
  return l + r;
}
