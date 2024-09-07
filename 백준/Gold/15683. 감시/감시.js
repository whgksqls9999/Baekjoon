const input = require('fs').readFileSync(0).toString().trim().split('\n');

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

class CCTV {

    constructor(type, pos){
        this.type = type;
        this.pos = pos;
        this.dir = 0;
    }

    validate(r, c){
        if (r < 0 || c < 0 || r >= arr.length || c >= arr[r].length || arr[r][c] === 6){
            return false;
        }
        return true;
    }

    check(dir, checked){
        let cnt = 0;
        let nr = this.pos[0] + dr[dir];
        let nc = this.pos[1] + dc[dir];
        while(this.validate(nr, nc)){
            if (!checked[nr][nc] && arr[nr][nc] === 0){
                cnt++;
                checked[nr][nc] = true;
            }
            nr += dr[dir];
            nc += dc[dir];
        }
        return cnt;
    }

    getArea(checked){
        let cnt = 0;
        switch (this.type){
            case 1:
                cnt += this.check(this.dir, checked);
                break;
            case 2:
                for (let i = 0; i < 3; i += 2){
                    cnt += this.check((this.dir + i) % 4, checked);
                }
                break;
            case 3:
                for (let i = 0; i < 2; i++){
                    cnt += this.check((this.dir + i) % 4, checked);
                }
                break;
            case 4:
                for (let i = 0; i < 3; i++){
                    cnt += this.check((this.dir + i) % 4, checked);
                }
                break;
            case 5:
                for (let i = 0; i < 4; i++){
                    cnt += this.check((this.dir + i) % 4, checked);
                }
                break;
        }
        return cnt;
    }
}

let area = 0;
let answer = 0;
let arr = [];
function dfs(cctvs, idx, limit, N, M){
    if (idx === limit){
        let sum = 0;
        const checked = Array(N).fill().map(() => Array(M).fill(false));
        for (let cctv of cctvs){
            sum += cctv.getArea(checked);
        }
        answer = Math.min(area - sum, answer);
        return;
    }
    
    for (let i = 0; i < 4; i++){
        if (i > 0 && cctvs[idx].type === 5) continue;
        cctvs[idx].dir = i;
        dfs(cctvs, idx+1, limit, N, M);
    }
}

function solution(){
    let idx = 0;
    const [N, M] = input[idx++].split(' ').map(Number);
    area = answer = N * M;
    arr = Array(N).fill().map(() => input[idx++].trim().split(' ').map(Number));
    const cctvs = [];

    for (let i = 0; i < N; i++){
        for (let j = 0; j < M; j++){
            if (arr[i][j] !== 0){
                area--;
                if (arr[i][j] !== 6){
                    cctvs.push(new CCTV(arr[i][j], [i, j]));
                }
            }
        }
    }

    dfs(cctvs, 0, cctvs.length, N, M);

    return answer;
}


console.log(solution());