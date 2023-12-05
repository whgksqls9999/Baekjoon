const input = require("fs").readFileSync("/dev/stdin").toString();

let number = Number(input);

solution(number);

function solution(_number) {
  let answer = "";

  const arr = Array(_number)
    .fill(0)
    .map(() => Array(_number).fill([]));

  makeStar(_number, arr, 0, 0, false);

  for (let i = 0; i < arr.length; ++i) {
    answer += arr[i].join("");
    answer += "\n";
  }

  console.log(answer);
}

function makeStar(_num, _arr, _r, _c, _blank) {
  // if (_num === 1) {
  //   _arr[_r][_c] = "*";
  //   return;
  // }
  // for (let k = 0; k < _depth; k += 3) {
  //   let count = 0;
  //   for (let i = 0 + k; i < k + 3; ++i) {
  //     for (let j = 0 + k; j < k + 3; ++j) {
  //       if (count === 5) continue;
  //       console.log(i, j);
  //       ++count;
  //       makeStar(_num / 3, _arr, i, j, _depth / 3);
  //     }
  //   }
  // }

  if (_blank) {
    for (let i = _r; i < _r + _num; ++i) {
      for (let j = _c; j < _c + _num; ++j) {
        _arr[i][j] = " ";
      }
    }
    return;
  }

  if (_num === 1) {
    _arr[_r][_c] = "*";
    return;
  }

  let size = _num / 3;
  let cnt = 0;

  for (let i = _r; i < _r + _num; i += size) {
    for (let j = _c; j < _c + _num; j += size) {
      ++cnt;
      if (cnt == 5) {
        makeStar(size, _arr, i, j, true);
      } else {
        makeStar(size, _arr, i, j, false);
      }
    }
  }
}
