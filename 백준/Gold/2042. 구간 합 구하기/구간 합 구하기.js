// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
// 이건 백준에서 제출시 인풋 받기

const init = function (node, start, end) {
    if (start === end) {
        tree[node] = arr[start];
        return;
    }

    let mid = ~~((start + end) / 2);
    init(node * 2, start, mid);
    init(node * 2 + 1, mid + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

const update = function (node, start, end, idx, val) {
    if (idx < start || end < idx) {
        return;
    }

    if (start === end) {
        arr[idx] = val;
        tree[node] = val;
        return;
    }

    let mid = ~~((start + end) / 2);
    update(node * 2, start, mid, idx, val);
    update(node * 2 + 1, mid + 1, end, idx, val);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

const query = function (node, start, end, left, right) {
    if (right < start || end < left) {
        return BigInt(0);
    }

    if (left <= start && end <= right) {
        return tree[node];
    }

    let mid = Math.floor(((start + end) / 2));
    let l = query(node * 2, start, mid, left, right);
    let r = query(node * 2 + 1, mid + 1, end, left, right);
    return BigInt(l + r);
}


let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

let [N, M, K] = input[0].split(' ');
[N, M, K] = [N, M, K].map((element) => {
    return parseInt(element);
})

let arr = [];
for (let i = 0; i < N; ++i) {
    arr.push(BigInt(input[i + 1]));
}

let tree = new Array(4 * N).fill(BigInt(0));

init(1, 0, N - 1);

let ans =[];
for (let i = 1 + N; i < N + M + K + 1; ++i) {
    let [a, b, c] = input[i].split(' ').map((element) => {
        return parseInt(element);
    });

    if (a === 1) {
        c = BigInt(c);
        update(1, 0, N - 1, b - 1, c);
    } else {
        ans.push(query(1, 0, N - 1, b - 1, c - 1).toString(10));
    }
}

console.log(ans.join('\n'));
