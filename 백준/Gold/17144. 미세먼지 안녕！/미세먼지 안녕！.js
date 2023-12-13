const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
let [R, C, T] = input[0].split(" ").map((element) => Number(element));
const arr = Array(R).fill(0);

solution();

function solution() {
  // idx : 공기청정기 위치를 담을 배열
  let idx = [];

  // 배열 생성
  for (let i = 0; i < R; ++i) {
    arr[i] = input[i + 1].split(" ").map((element) => Number(element));
    for (let j = 0; j < C; ++j) {
      if (arr[i][j] === -1) {
        idx.push([i, j]);
      }
    }
  }

  // 먼지 확산 -> 공기청정기 순환
  for (let i = 0; i < T; ++i) {
    doDustMove(idx);
    doCirculate(idx, 0);
    doCirculate(idx, 1);
  }

  // 정답 출력
  let answer = 0;
  for (let i = 0; i < R; ++i) {
    for (let j = 0; j < C; ++j) {
      if (arr[i][j] > 0) {
        answer += arr[i][j];
      }
    }
  }
  console.log(answer);
}

/**
 * 먼지 확산 알고리즘
 * @param {Object} idx
 */
function doDustMove(idx) {
  // 임시 배열 생성
  const tmpArr = Array(R)
    .fill(0)
    .map(() => Array(C).fill(0));

  // 공기청정기 위치 설정
  for (let i = 0; i < 2; ++i) {
    tmpArr[idx[i][0]][idx[i][1]] = -1;
  }

  // 먼지 확산
  for (let i = 0; i < R; ++i) {
    for (let j = 0; j < C; ++j) {
      if (arr[i][j] > 0) {
        let diffusion = Math.floor(arr[i][j] / 5);
        let cnt = 0;
        for (let k = 0; k < 4; ++k) {
          let nr = i + dr[k];
          let nc = j + dc[k];
          if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
            if (arr[nr][nc] !== -1) {
              ++cnt;
              tmpArr[nr][nc] += diffusion;
            }
          }
        }
        tmpArr[i][j] += arr[i][j] - diffusion * cnt;
      }
    }
  }

  // 기존 배열 갱신
  for (let i = 0; i < R; ++i) {
    arr[i] = [...tmpArr[i]];
  }
}

/**
 * 공기청정기 순환 알고리즘
 * @param {Ojbect} idx
 * @param {Number} dir
 */
function doCirculate(idx, dir) {
  let [r, c] = idx[dir];

  // 공기청정기 상부 순환
  if (dir === 0) {
    while (r - 1 >= 0) {
      if (arr[r][c] === -1) {
        arr[r - 1][c] = 0;
        --r;
        continue;
      }
      arr[r][c] = arr[r - 1][c];
      --r;
    }
    while (c + 1 < C) {
      arr[r][c] = arr[r][c + 1];
      ++c;
    }
    while (r + 1 <= idx[dir][0]) {
      arr[r][c] = arr[r + 1][c];
      ++r;
    }
    while (c - 1 > 0) {
      arr[r][c] = arr[r][c - 1];
      --c;
    }
    arr[r][c] = 0;
  }

  // 공기청정기 하부 순환
  else if (dir === 1) {
    while (r + 1 < R) {
      if (arr[r][c] === -1) {
        arr[r + 1][c] = 0;
        ++r;
        continue;
      }
      arr[r][c] = arr[r + 1][c];
      ++r;
    }
    while (c + 1 < C) {
      arr[r][c] = arr[r][c + 1];
      ++c;
    }
    while (r - 1 >= idx[dir][0]) {
      arr[r][c] = arr[r - 1][c];
      --r;
    }
    while (c - 1 > 0) {
      arr[r][c] = arr[r][c - 1];
      --c;
    }
    arr[r][c] = 0;
  }
}
