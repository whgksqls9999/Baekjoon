const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

class Circle {
  constructor(x, y, r) {
    this.x = x;
    this.y = y;
    this.r = r;
  }
}

solution();

function solution() {
  let N = Number(input[0]);

  let answer = [];
  for (let tc = 1; tc <= N; ++tc) {
    let info = input[tc].split(" ").map((element) => Number(element));
    let circleA = new Circle(info[0], info[1], info[2]);
    let circleB = new Circle(info[3], info[4], info[5]);
    let distance = Math.sqrt(
      Math.abs(circleA.x - circleB.x) ** 2 +
        Math.abs(circleA.y - circleB.y) ** 2
    );

    if (
      circleA.x === circleB.x &&
      circleA.y === circleB.y &&
      circleA.r === circleB.r
    ) {
      answer.push(-1);
    } else if (distance > circleA.r + circleB.r) {
      answer.push(0);
    } else if (distance == circleA.r + circleB.r) {
      answer.push(1);
    } else if (distance < circleA.r + circleB.r) {
      if (distance > Math.abs(circleA.r - circleB.r)) {
        answer.push(2);
      } else if (distance === Math.abs(circleA.r - circleB.r)) {
        answer.push(1);
      } else {
        answer.push(0);
      }
    }
  }
  console.log(answer.join("\n"));
}
