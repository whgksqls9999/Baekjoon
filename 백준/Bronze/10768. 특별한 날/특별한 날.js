const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const [N, M] = [input[0].trim(), input[1].trim()];

    const sum = N + M;

    if (sum > "218") {
        console.log("After");
    } else if (sum < "218") {
        console.log("Before");
    } else {
        console.log("Special");
    }
})();
