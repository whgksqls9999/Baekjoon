const input = require('fs').readFileSync(0).toString().trim().split('\n');

let idx = 0;
let T = Number(input[idx++]);

for (let tc = 0; tc < T; tc++) {
    let [N, M, W] = input[idx++].split(' ').map(Number);

    const edges = [];
    for (let i = 0; i < M; i++) {
        let [s, e, d] = input[idx++].split(' ').map(Number);
        edges.push([s, e, d])
        edges.push([e, s, d])
    }

    for (let i = 0; i < W; i++) {
        let [s, e, d] = input[idx++].split(' ').map(Number);
        edges.push([s, e, -d])
    }

    let check = false;
    const INF = 2500 * 10000;

    const visited = Array(N + 1).fill(INF);

    outer: for (let j = 1; j <= N; j++) {
        for (let [s, e, d] of edges) {
            if (visited[s] + d < visited[e]) {
                visited[e] = visited[s] + d;

                if (j === N) {
                    check = true;
                    break outer;
                }
            }
        }
    }
    console.log(check ? 'YES' : 'NO');
}
