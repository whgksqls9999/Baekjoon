const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    let [N, F] = [Number(input[0]), Number(input[1])];

    let answer = N.toString()
        .substring(0, N.toString().length - 2)
        .concat("00");

    for (let i = Number(answer); i <= Number(answer + 99); i++) {
        if (i % F === 0) {
            answer = i.toString();
            break;
        }
    }

    console.log(answer.slice(answer.length - 2));
})();
