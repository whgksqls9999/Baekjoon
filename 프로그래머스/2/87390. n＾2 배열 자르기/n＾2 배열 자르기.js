function solution(n, left, right) {
    var answer = [];
    
    for(let i = left; i <= right; i++){
        // i 번째 숫자가 n * n 배열에서의 몇 행 몇 열인지 계산
        let [row, col] = [Math.floor(i / n) + 1, Math.floor(i % n) + 1];
        
        // 행, 열 번호 중 더 큰 번호로 배열이 채워졌을 것이므로 둘 중 더 큰 수를 answer 배열에 추가
        answer.push(Math.max(row, col));
    }
    
    return answer;
}