function solution(array) {
    var answer = 0;
    
    let max = 0;
    let arr = new Array(1000).fill(0);
    array.forEach((element) => {
        arr[element]++;
        if (max < arr[element]){
            max = arr[element];
            answer = element;
        }
    })
    
    let check = false;
    arr.forEach((element, i) => {
        if (max === element){
            if(!check){
                check = true;
                answer = i;
            } else {
                answer = -1;
            }
        }
    })
    
    
    return answer;
}