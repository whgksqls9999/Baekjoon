// 풀이 전략: 슬라이딩 윈도우 알고리즘을 통해 O(n) 시간에 계산하기

function solution(want, number, discount) {
    var answer = 0;
    
    // WANT : {원하는 물건: 개수}
    const WANT = new Map();
    
    // PRODUCT : {할인 품목: 개수}
    const PRODUCT = new Map();
    
    // 원하는 물건 Map 셋팅
    for (let i = 0; i < want.length; i++){
        WANT.set(want[i], number[i]);
    }
    
    // 할인 물건 Map 셋팅
    for (let i = 0; i < 10; i++){
        const product = discount[i];
        
        if (PRODUCT.has(product)){
            PRODUCT.set(product, PRODUCT.get(product) + 1);
        } else {
            PRODUCT.set(product, 1);
        }
    }
    
    let left = 0, right = 10;
    
    // 슬라이딩 윈도우
    while (right <= discount.length){
        let check = true;
        
        // 원하는 품목, 할인 품목 체크
        for (let i of WANT.keys()){
            if (WANT.get(i) > (PRODUCT.get(i) ?? 0)){
                check = false;
                break;
            }
        }

        if (check) {
            answer++;
        }
        
        // 첫날 상품 뺴기
        PRODUCT.set(discount[left], PRODUCT.get(discount[left++]) - 1);
        // 마지막 다음 날 할인상품 넣기
        PRODUCT.set(discount[right], (PRODUCT.get(discount[right++]) ?? 0) + 1);
    }
    
    return answer;
}