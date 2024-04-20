function solution(n) {
    let answer = 0;
    
    let INF = 1234567;

    let a = 1, b = 2;
    
    for (let i = 3; i <= n; i++){
        [a, b] = [b, a];
        b = (a + b) % INF;
    }

    return n === 1 ? 1 : b;
}