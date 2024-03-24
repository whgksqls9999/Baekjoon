const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    let [N, d, k, c] = input[0].split(" ").map(Number);

    const arr = init(N, k);
    const set = Array(d + 1).fill(0);

    for (let i = 0; i < k; i++) {
        set[arr[i]]++;
    }
    set[c]++;

    let left = 0,
        right = k - 1;

    let answer = 0;
    let size = 0;

    for (; right < arr.length; ) {
        size = 0;

        for (let i = 1; i <= d; i++) {
            if (set[i] > 0) {
                size++;
            }
        }

        answer = Math.max(answer, size);

        set[arr[left++]]--;
        set[arr[++right]]++;
    }

    console.log(answer);
})();

function init(N, k) {
    const tmp = [];

    for (let i = 1; i <= N; i++) {
        tmp.push(Number(input[i]));
    }

    for (let i = 1; i < k; i++) {
        tmp.push(Number(input[i]));
    }

    return tmp;
}
