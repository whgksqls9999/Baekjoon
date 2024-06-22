let arrLength, startPosition, escapePosition, limitCount;

const dr = [1, 0, 0, -1];
const dc = [0, -1, 1, 0];
const commandKeyword = ['d','l','r','u'];

function solution(n, m, x, y, r, c, k) {
    var answer = '';
    
    arrLength = [n, m];
    startPosition = [x, y];
    escapePosition = [r, c];
    limitCount = k;
    
    if (!validateDistance(startPosition, escapePosition, limitCount)){
        return "impossible";
    }
    
    const minDist = calculateDist(startPosition, escapePosition);
    
    const edgePosition = [n, 1];
    const startToEdge = calculateDist(startPosition, edgePosition);
    const edgeToEscape = calculateDist(edgePosition, escapePosition);
    const maxDist = mergeObject(startToEdge, edgeToEscape);
    const minTotalDist = getTotalDist(minDist);
    const maxTotalDist = getTotalDist(maxDist);
    
    if (minTotalDist > k){
        answer = 'impossible';
    } else if (minTotalDist === k){
        answer = returnResult(minDist);
    } else if (k > minTotalDist && k < maxTotalDist){
        let diff = maxTotalDist - k;
        
        maxDist['r'] -= diff/2;
        maxDist['l'] -= diff/2;
        
        answer = returnResult(maxDist);
    } else if (maxTotalDist === k){
        answer = returnResult(maxDist);
    } else if (k > maxTotalDist){
        let diff = k - maxTotalDist;
        
        maxDist['r'] += diff/2;
        maxDist['l'] += diff/2;
        
        answer = returnResult(maxDist);
    }
        return answer;
}

function returnResult(object){
    let tmp = '';
    let cnt = getTotalDist(object);
    let [r, c] = startPosition;
        
    while(cnt !== 0){
        for (let i = 0; i < 4; i++){
            if (!object[commandKeyword[i]]) continue;
             
            let nr = r + dr[i];
            let nc = c + dc[i];
            
            if (isValid(nr, nc)){
                tmp += commandKeyword[i];
                object[commandKeyword[i]] -= 1;
                cnt--;
                r = nr;
                c = nc;
                break;
            }
        }
    }

    return tmp;
}

function isValid(r, c){
    return r > 0 && r <= arrLength[0] && c > 0 && c <= arrLength[1];
}

function calculateDist(aPosition, bPosition){
    let tmp = {
        'd': 0,
        'l': 0,
        'r': 0,
        'u': 0,
    };
// 
    let horizontal = bPosition[1] - aPosition[1];
    let vertical = aPosition[0] - bPosition[0];
    
    if (horizontal > 0 ){
        tmp.r = horizontal;
    } else {
        tmp.l = Math.abs(horizontal);
    }
    
    if (vertical > 0){
        tmp.u = vertical;
    } else {
        tmp.d = Math.abs(vertical);
    }
    
    return tmp;
}

function mergeObject(a, b){
    const tmp = {
        'd': 0,
        'l': 0,
        'r': 0,
        'u': 0,
    }
    
    for (let key in a){
        tmp[key] += a[key] + b[key];
    }
        
    return tmp;
}

function getTotalDist(object){
    let sum = 0;
    for (let key in object){
        sum += object[key];
    }
    return sum;
}

function validateDistance(startPosition, escapePosition, limitCount){
    let dist = 0;
    
    dist += Math.abs(escapePosition[0] - startPosition[0]);
    dist += Math.abs(escapePosition[1] - startPosition[1]);
    
    return (dist % 2 === 0) === (limitCount % 2 === 0);
}