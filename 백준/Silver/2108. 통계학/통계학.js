// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

console.log(solution().join("\n"));

function solution() {
  let N = Number(input[0]);

  let arr = Array(N);
  for (let i = 1; i <= N; ++i) {
    arr[i - 1] = Number(input[i]);
  }

  arr.sort((a, b) => a - b);

  let avg = 0;
  arr.forEach((element) => (avg += element));
  avg /= arr.length;
  avg = Math.round(avg);

  let mid = arr[Math.floor(arr.length / 2)];

  let arrLength = arr[arr.length - 1] + 1;
  let min = 0;
  if (arr[0] < 0) {
    arrLength -= arr[0];
    min = arr[0];
  }

  let freArr = Array(arrLength).fill(0);

  let max = 0;

  for (let i = 0; i < arr.length; ++i) {
    freArr[arr[i] - min]++;
    if (max < freArr[arr[i] - min]) {
      max = freArr[arr[i] - min];
    }
  }

  let fre = -4001;
  for (let i = 0; i < freArr.length; ++i) {
    if (freArr[i] === max) {
      if (fre !== -4001) {
        fre = i + min;
        break;
      }
      fre = i + min;
    }
  }

  let ran = arr[arr.length - 1] - arr[0];

  return [avg, mid, fre, ran];
}
