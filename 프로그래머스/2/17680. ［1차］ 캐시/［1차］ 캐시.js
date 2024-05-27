// 풀이 전략: 배열 순회하면서 Map에 도시 이름이랑 캐싱 순서 저장, 캐시 크기와 캐싱 순서 고려하여 캐싱 여부 체크
// 시간복잡도: O(n);

function solution(cacheSize, cities) {
    var answer = 0;
    
    const cache = new Map();
    
    let limit = 0;
    cities.forEach((it, idx) => {
        // 도시 명 소문자로 변환
        const city = it.toLowerCase();
        
        // 캐시에 없다면
        if (!cache.has(city)){
            
            answer += 5;
            
            let min = limit + 1;
            for (let cur of cache.values()){
                min = Math.min(min, cur);
            }
            
            if (cache.size === cacheSize){
                for (let cur of cache.keys()){
                    if (cache.get(cur) === min){
                        cache.delete(cur);
                        cache.set(city, ++limit);
                        break;
                    }
                } 
            } else {
                cache.set(city, ++limit);    
            }
            
        } else {
            answer++;
            cache.set(city, ++limit);
        }
    })
    
    return answer;
}