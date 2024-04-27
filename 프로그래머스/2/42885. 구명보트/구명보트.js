function solution(people, limit) {
    var answer = 0;
    
    people.sort((a, b) => a - b);
    
    let min = 0;
    let max = people.length - 1;
    
    while(min <= max){        
        let cur = people[max];
        
        if (limit - cur >= people[min]){
            cur += people[min++];
        }
        
        max--;
        answer++;
    }
    
    return answer;
}