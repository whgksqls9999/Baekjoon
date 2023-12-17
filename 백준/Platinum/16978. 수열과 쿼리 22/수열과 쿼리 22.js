// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const N = Number(input[0]);
const arr = input[1].split(" ").map((element) => BigInt(element));
const tree = Array(400000).fill(BigInt(0));
init(1, 0, N - 1);

(function solution() {
  const M = Number(input[2]);
  const updateArr = [];
  const queryArr = [];

  // 반복문을 통해 입력받은 업데이트, 조회 쿼리를 배열에 저장

  // order : 조회 쿼리의 순서를 기억할 수 있도록 순서 저장
  let order = -1;
  for (let i = 0; i < M; ++i) {
    // 쿼리 입력받기
    let command = input[3 + i].split(" ").map((element) => Number(element));

    // pushedArr : 입력받은 쿼리를 저장할 배열의 주소를 가질 변수
    let puhsedArr;
    if (command[0] === 1) {
      puhsedArr = updateArr;
    } else if (command[0] === 2) {
      puhsedArr = queryArr;
      ++order;
    }
    puhsedArr.push([...command.slice(1, command.length), order]);
  }

  // 조회 쿼리를 k값 기준으로 오름차순 정렬
  queryArr.sort((a, b) => a[0] - b[0]);

  // answer : 정답 저장할 배열
  const answer = Array(queryArr.length);

  // updated : 트리의 업데이트가 몇번째까지 진행됐는지 저장
  let updated = 0;
  for (query of queryArr) {
    while (query[0] > updated) {
      let updateQuery = updateArr[updated++];
      doUpdate(1, 0, N - 1, updateQuery[0] - 1, updateQuery[1]);
    }
    answer[query[3]] = doQuery(1, 0, N - 1, query[1] - 1, query[2] - 1);
  }
  console.log(answer.join("\n"));
})();

function init(node, start, end) {
  if (start === end) {
    tree[node] = arr[start];
    return;
  }

  let mid = Math.floor((start + end) / 2);
  init(node * 2, start, mid);
  init(node * 2 + 1, mid + 1, end);
  tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

function doUpdate(node, start, end, idx, val) {
  if (idx < start || end < idx) {
    return;
  }

  if (start === end) {
    tree[node] = BigInt(val);
    return;
  }

  let mid = Math.floor((start + end) / 2);
  doUpdate(node * 2, start, mid, idx, val);
  doUpdate(node * 2 + 1, mid + 1, end, idx, val);
  tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

function doQuery(node, start, end, left, right) {
  if (right < start || end < left) {
    return BigInt(0);
  }

  if (left <= start && end <= right) {
    return BigInt(tree[node]);
  }

  let mid = Math.floor((start + end) / 2);
  let l = doQuery(node * 2, start, mid, left, right);
  let r = doQuery(node * 2 + 1, mid + 1, end, left, right);
  return l + r;
}
