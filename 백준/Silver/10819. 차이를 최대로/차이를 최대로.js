const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

let answer = 0;

(function solution() {
    const N = Number(input[0]);
    const arr = input[1].split(" ").map(Number);

    dfs(0, N, Array(N).fill(0), Array(N).fill(false), arr);

    console.log(answer);
})();

function dfs(cur, limit, selected, used, arr) {
    if (cur === limit) {
        let res = 0;

        for (let i = 0; i < selected.length - 1; i++) {
            res += Math.abs(selected[i] - selected[i + 1]);
        }

        answer = Math.max(answer, res);
        return;
    }

    for (let i = 0; i < arr.length; i++) {
        if (used[i]) continue;

        used[i] = true;
        selected[cur] = arr[i];
        dfs(cur + 1, limit, selected, used, arr);
        selected[cur] = 0;
        used[i] = false;
    }
}
