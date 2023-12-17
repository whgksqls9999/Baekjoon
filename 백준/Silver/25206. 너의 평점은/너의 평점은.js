// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

// 초기 값 설정
const map = new Map();
map.set("A+", 4.5);
map.set("A0", 4.0);
map.set("B+", 3.5);
map.set("B0", 3.0);
map.set("C+", 2.5);
map.set("C0", 2.0);
map.set("D+", 1.5);
map.set("D0", 1.0);
map.set("F", 0.0);

(function solution() {
  let answer = 0;
  let cnt = 0;
  for (let i = 0; i < 20; ++i) {
    let cur = input[i].split(" ").map((element) => element.trim());
    if (cur[2] === "P") {
      continue;
    }

    answer += Number(cur[1]) * map.get(cur[2]);
    cnt += Number(cur[1]);
  }
  // console.log(answer / cnt);
  console.log((answer / cnt).toFixed(6));
})();
