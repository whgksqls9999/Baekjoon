function solution(arr) {
    var answer = [];
    
    answer = comp(arr, 0, 0, arr.length);
    
    return answer;
}

function comp(arr, _r, _c, size){
    let num = arr[_r][_c];
    
    let check = true;
    
    outer: for (let i = _r; i < _r + size; i++){
        for (let j = _c; j < _c + size; j++){
            if (arr[i][j] !== num){
                check = false;
                break outer;
            }
        }
    }
    
    if (check){
        const res = [0,0];
        res[num]++;
        return res;
    } 
    
    const res = [0, 0];
    let a = comp(arr, _r, _c, size/2);
    let b = comp(arr, _r + size/2, _c, size/2);
    let c = comp(arr, _r, _c + size/2, size/2);
    let d = comp(arr, _r + size/2, _c + size/2, size/2);
    
    res[0] = res[0] + a[0] + b[0] + c[0] + d[0];
    res[1] = res[1] + a[1] + b[1] + c[1] + d[1];
        
    return res;
}