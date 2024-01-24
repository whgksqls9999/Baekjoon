const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);

const h = Math.ceil(Math.log(N) / Math.log(2));
const size = 1 << (h + 1);
const tree = Array(size).fill(0);

let answer = 0;

(function solution() {
  init(1, 1, N);
  console.log(answer);
})();

function init(node, start, end) {
  if (start === end) {
    tree[node] = [arr[start - 1]];
    return;
  }

  let mid = Math.floor((start + end) / 2);
  init(node * 2, start, mid);
  init(node * 2 + 1, mid + 1, end);
  sort(node, tree[node * 2], tree[node * 2 + 1]);
}

function sort(node, left, right) {
  let [l, r] = [0, 0];
  const tmp = [];

  while (l < left.length && r < right.length) {
    if (left[l] > right[r]) {
      answer += left.length - l;
      tmp.push(right[r++]);
    } else if (left[l] < right[r]) {
      tmp.push(left[l++]);
    } else {
      tmp.push(left[l++]);
    }
  }

  while (l < left.length) {
    tmp.push(left[l++]);
  }

  while (r < right.length) {
    tmp.push(right[r++]);
  }
  tree[node] = tmp;
}
