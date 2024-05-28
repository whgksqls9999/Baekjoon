// 풀이 전략: 해싱, 정렬 O(n^2)

function solution(genres, plays) {
    var answer = [];
    
    let map = new Map();
    
    genres.forEach((genre, idx) => {
        const play = plays[idx];
        
        if (!map.has(genre)){
            map.set(genre, []);
        }
        
        map.get(genre).push({num: idx, play});
    })
    
    for (let key of map.keys()){
        let arr = map.get(key);
        arr.sort((a, b) => {
            if (a.play !== b.play){
                return b.play - a.play;
            } else {
                return a.num - b.num;
            }
        })
    }
    
    map = new Map([...map].sort((a, b) => {
        const sumA = a[1].reduce((prev, cur) => prev + cur.play, 0);
        const sumB = b[1].reduce((prev, cur) => prev + cur.play, 0);
        
        return sumB - sumA;
    }));
        
    for (let genre of map){        
        genre[1].forEach((it, idx) => {
            if (idx >= 2) return;
            answer.push(it.num);
        })
    }
    
    return answer;
}