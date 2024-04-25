function solution(elements) {
    var answer = 0;
    
    const set = new Set();
    
    const arr = [0];
    for (let i of elements){
        arr.push(i);
        set.add(i);
    }
    
    for (let i = 0; i < elements.length - 2; i++){
        arr.push(elements[i]);
    }
    
    const prefixSum = Array(arr.length).fill(0);
    for (let i = 1; i < prefixSum.length; i++){
        prefixSum[i] = prefixSum[i-1] + arr[i];
    }
    
    for (let i = 1; i < prefixSum.length; i++){
        for (let j = Math.max(i - elements.length, 0); j < i; j++){
            set.add(prefixSum[i] - prefixSum[j]);
        }
    }
    
    return set.size;
}