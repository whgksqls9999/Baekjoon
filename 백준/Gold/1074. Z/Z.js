const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

const [N, r, c] = input[0].split(" ").map(Number);

console.log(divide(2 ** N, r, c));

function divide(_n, _r, _c) {
    let curN = Math.floor(_n / 2);

    let response = 0;

    if (curN === 0) {
        return 0;
    }

    if (_r < curN && _c < curN) {
        response += divide(curN, _r, _c);
    } else if (_r < curN && _c >= curN) {
        response += curN * curN * 1 + divide(curN, _r, _c - curN);
    } else if (_r >= curN && _c < curN) {
        response += curN * curN * 2 + divide(curN, _r - curN, _c);
    } else if (_r >= curN && _c >= curN) {
        response += curN * curN * 3 + divide(curN, _r - curN, _c - curN);
    }

    return response;
}
