const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);
const tree = Array(4 * arr.length).fill([]);
init(1, 0, N - 1);

(function solution() {
  const answer = [];
  const M = Number(Number(input[2]));
  for (let i = 0; i < M; ++i) {
    let [l, r, k] = input[3 + i].split(" ").map(Number);
    if (i > 0) {
      l = l ^ answer[i - 1];
      r = r ^ answer[i - 1];
      k = k ^ answer[i - 1];
    }
    answer.push(query(1, 0, N - 1, l - 1, r - 1, k));
  }
  console.log(answer.join("\n"));
})();

function init(node, start, end) {
  if (start === end) {
    tree[node] = [arr[start]];
    return;
  }

  let mid = Math.floor((start + end) / 2);
  init(node * 2, start, mid);
  init(node * 2 + 1, mid + 1, end);
  tree[node] = tree[node * 2].concat(tree[node * 2 + 1]);
  sort(node);
}

function sort(node) {
  tree[node] = [];

  let l = 0;
  let r = 0;
  let lMax = tree[node * 2].length;
  let rMax = tree[node * 2 + 1].length;

  while (l < lMax && r < rMax) {
    if (tree[node * 2][l] <= tree[node * 2 + 1][r]) {
      tree[node].push(tree[node * 2][l++]);
    } else {
      tree[node].push(tree[node * 2 + 1][r++]);
    }
  }

  while (l < lMax) {
    tree[node].push(tree[node * 2][l++]);
  }
  while (r < rMax) {
    tree[node].push(tree[node * 2 + 1][r++]);
  }
}

function query(node, start, end, left, right, k) {
  if (end < left || right < start) {
    return 0;
  }

  if (left <= start && end <= right) {
    let cnt = 0;
    let l = 0;
    let r = tree[node].length - 1;
    while (l < r) {
      let mid = Math.floor((l + r) / 2);
      if (tree[node][mid] < k) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    if (tree[node][r] > k) {
      cnt = tree[node].length - r;
    } else if (r !== tree[node].length - 1 && l !== 0) {
      cnt = tree[node].length - r - 1;
    }

    return cnt;
  }

  let mid = Math.floor((start + end) / 2);
  let l = query(node * 2, start, mid, left, right, k);
  let r = query(node * 2 + 1, mid + 1, end, left, right, k);
  return l + r;
}
