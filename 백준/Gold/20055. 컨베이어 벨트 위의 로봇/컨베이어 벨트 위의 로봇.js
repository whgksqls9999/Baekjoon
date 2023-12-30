const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, K] = input[0].split(" ").map(Number);

class Conveyor {
  constructor(durabilities) {
    // 벨트 설정 [내구도, 로봇 존재 여부]
    this.belt = Array(2 * N)
      .fill(0)
      .map((element, idx) => [durabilities[idx], false]);
    this.liftPoint = 0; // 로봇 상차지점
    this.dropPoint = N - 1; // 로봇 하차지점
    this.breakPoints = 0; // 고장난 칸 개수
  }

  // 벨트 회전시키기
  rotateBelt() {
    this.liftPoint--;
    this.dropPoint--;

    // 상차, 하차지점이 벨트를 벗어났을 경우 보정
    if (this.liftPoint < 0) {
      this.liftPoint += 2 * N;
    }
    if (this.dropPoint < 0) {
      this.dropPoint += 2 * N;
    }

    // 로봇 하차
    this.dropRobot();
  }

  // 로봇 상차하기
  liftRobot() {
    // 상차지점의 내구도가 0보다 크고 로봇이 없을 때
    if (this.belt[this.liftPoint][0] > 0 && !this.belt[this.liftPoint][1]) {
      this.belt[this.liftPoint][1] = true;
      this.belt[this.liftPoint][0]--;
      this.checkDurability(this.liftPoint);
    }
  }

  // 로봇 하차하기
  dropRobot() {
    if (this.belt[this.dropPoint][1]) {
      this.belt[this.dropPoint][1] = false;
    }
  }

  // 로봇 이동시키기
  moveRobot() {
    for (let i = this.dropPoint; i > this.dropPoint - N; i--) {
      let cur = i >= 0 ? i : i + 2 * N;
      let pre = cur - 1 >= 0 ? cur - 1 : cur - 1 + 2 * N;

      // 현재 보고있는 컨베이어 벨트 칸의 내구도가 0보다 크고 로봇이 없을 때, 이동시킬 로봇이 존재할 때
      if (this.belt[cur][0] > 0 && !this.belt[cur][1] && this.belt[pre][1]) {
        this.belt[cur][0]--; // 내구도 감소
        this.belt[cur][1] = true; // 로봇 이동
        this.belt[pre][1] = false; // 로봇 이동
        this.checkDurability(cur);
      }
    }

    this.dropRobot();
  }

  checkDurability(num) {
    if (this.belt[num][0] === 0) {
      this.breakPoints++;
    }
  }
}

(function solution() {
  const conveyor = new Conveyor(input[1].split(" ").map(Number));

  let i = 0;
  while (conveyor.breakPoints < K) {
    // console.log(conveyor);
    conveyor.rotateBelt();
    conveyor.moveRobot();
    conveyor.liftRobot();
    ++i;
  }
  console.log(i);
})();
