const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);
const M = Number(input[2]);

const h = Math.ceil(Math.log(N) / Math.log(2));
const size = 1 << (h + 1);
const tree = Array(size)
  .fill(0)
  .map(() => Array(2).fill(0));

(function solution() {
  const answer = [];

  init(1, 1, N);

  for (let i = 0; i < M; i++) {
    const [type, a, b] = input[i + 3].split(" ").map(Number);

    if (type === 1) {
      update(1, 1, N, a, b);
    } else if (type === 2) {
      answer.push(queryEven(1, 1, N, a, b));
    } else {
      answer.push(queryOdd(1, 1, N, a, b));
    }
  }

  console.log(answer.join("\n"));
})();

function init(node, start, end) {
  if (start === end) {
    if (arr[start - 1] % 2 === 0) {
      tree[node][0]++;
    } else {
      tree[node][1]++;
    }
    return;
  }

  let mid = Math.floor((start + end) / 2);
  init(node * 2, start, mid);
  init(node * 2 + 1, mid + 1, end);
  tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
  tree[node][1] = tree[node * 2][1] + tree[node * 2 + 1][1];
}

function update(node, start, end, idx, val) {
  if (idx < start || end < idx) {
    return;
  }
  if (start === end) {
    if (arr[idx - 1] % 2 !== val % 2) {
      arr[idx - 1] = val;
      if (val % 2 === 0) {
        tree[node][0]++;
        tree[node][1]--;
      } else {
        tree[node][0]--;
        tree[node][1]++;
      }
    }
    return;
  }

  let mid = Math.floor((start + end) / 2);
  update(node * 2, start, mid, idx, val);
  update(node * 2 + 1, mid + 1, end, idx, val);
  tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
  tree[node][1] = tree[node * 2][1] + tree[node * 2 + 1][1];
}

function queryEven(node, start, end, left, right) {
  if (end < left || right < start) {
    return 0;
  }

  if (left <= start && end <= right) {
    return tree[node][0];
  }

  let mid = Math.floor((start + end) / 2);
  let l = queryEven(node * 2, start, mid, left, right);
  let r = queryEven(node * 2 + 1, mid + 1, end, left, right);
  return l + r;
}

function queryOdd(node, start, end, left, right) {
  if (end < left || right < start) {
    return 0;
  }

  if (left <= start && end <= right) {
    return tree[node][1];
  }

  let mid = Math.floor((start + end) / 2);
  let l = queryOdd(node * 2, start, mid, left, right);
  let r = queryOdd(node * 2 + 1, mid + 1, end, left, right);
  return l + r;
}
