// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
const [N, M] = input[0].split(" ").map((element) => Number(element));
let answer = "";

console.log(solution());

function solution() {
  const arr = Array(N)
    .fill(0)
    .map(() => Array(M).fill(0));

  for (let i = 1; i <= N; ++i) {
    let col = input[i].split(" ").map((element) => Number(element));
    for (let j = 0; j < M; ++j) {
      arr[i - 1][j] = col[j];
    }
  }

  for (let i = 0; i < N; ++i) {
    for (let j = 0; j < M; ++j) {
      for (let k = 0; k < 4; ++k) {
        linear(arr, i, j, k, 0, arr[i][j]);
        z(arr, i, j, k, 0, arr[i][j], k);
        t(arr, i, j, k);
        L(arr, i, j, k, 0, arr[i][j]);
      }
      square(arr, i, j, 0, 0, arr[i][j]);
    }
  }

  return answer;
}

/**
 * 함수 밖에서 for문을 통해 4방 _dir 지정 필요
 * 함수 밖에서 _sum을 arr[r][c]로 지정 필요
 * @param {*} _arr
 * @param {*} _r
 * @param {*} _c
 * @param {*} _dir
 * @param {*} _depth
 * @param {*} _sum
 * @returns
 */
function linear(_arr, _r, _c, _dir, _depth, _sum) {
  if (_depth === 3) {
    if (answer < _sum) {
      answer = _sum;
    }
    return;
  }

  let nr = _r + dr[_dir];
  let nc = _c + dc[_dir];
  if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
    linear(_arr, nr, nc, _dir, _depth + 1, _sum + _arr[nr][nc]);
  }
}

/**
 * 함수 밖에서 _dir을 0으로 지정 필요
 * 함수 밖에서 _sum을 arr[r][c]로 지정 필요
 * @param {*} _arr
 * @param {*} _r
 * @param {*} _c
 * @param {*} _dir
 * @param {*} _depth
 * @param {*} _sum
 * @returns
 */
function square(_arr, _r, _c, _dir, _depth, _sum) {
  if (_depth === 3) {
    if (answer < _sum) {
      answer = _sum;
    }
    return;
  }

  let nr = _r + dr[_dir];
  let nc = _c + dc[_dir];
  if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
    square(_arr, nr, nc, _dir + 1, _depth + 1, _sum + _arr[nr][nc]);
  }
}

function L(_arr, _r, _c, _dir, _depth, _sum) {
  if (_depth === 3) {
    if (answer < _sum) {
      answer = _sum;
    }
    return;
  }

  if (_depth === 2) {
    let nDir = (_dir + 1) % 4;
    let nr = _r + dr[nDir];
    let nc = _c + dc[nDir];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      L(_arr, nr, nc, nDir, _depth + 1, _sum + _arr[nr][nc]);
    }

    nDir = _dir - 1 >= 0 ? _dir - 1 : _dir + 3;
    nr = _r + dr[nDir];
    nc = _c + dc[nDir];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      L(_arr, nr, nc, nDir, _depth + 1, _sum + _arr[nr][nc]);
    }

    return;
  }

  let nr = _r + dr[_dir];
  let nc = _c + dc[_dir];
  if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
    L(_arr, nr, nc, _dir, _depth + 1, _sum + _arr[nr][nc]);
  }
}

function z(_arr, _r, _c, _dir, _depth, _sum, _oDir) {
  if (_depth === 3) {
    if (answer < _sum) {
      answer = _sum;
    }
    return;
  }

  if (_depth === 1) {
    let nDir = (_dir + 1) % 4;
    let nr = _r + dr[nDir];
    let nc = _c + dc[nDir];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      z(_arr, nr, nc, nDir, _depth + 1, _sum + _arr[nr][nc], _oDir);
    }

    nDir = _dir - 1 >= 0 ? _dir - 1 : _dir + 3;
    nr = _r + dr[nDir];
    nc = _c + dc[nDir];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      z(_arr, nr, nc, nDir, _depth + 1, _sum + _arr[nr][nc], _oDir);
    }
    return;
  }

  if (_depth === 2) {
    let nr = _r + dr[_oDir];
    let nc = _c + dc[_oDir];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      z(_arr, nr, nc, _dir, _depth + 1, _sum + _arr[nr][nc], _oDir);
    }
    return;
  }

  let nr = _r + dr[_dir];
  let nc = _c + dc[_dir];
  if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
    z(_arr, nr, nc, _dir, _depth + 1, _sum + _arr[nr][nc], _oDir);
  }
}

function t(_arr, _r, _c, _dir) {
  let sum = _arr[_r][_c];

  for (let i = 0; i < 3; ++i) {
    let nr = _r + dr[(_dir + i) % 4];
    let nc = _c + dc[(_dir + i) % 4];
    if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
      sum += _arr[nr][nc];
    }
  }

  if (answer < sum) {
    answer = sum;
  }
}
