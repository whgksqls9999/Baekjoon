// 풀이 전략
// 1. 우선순위, 처리여부를 담고 있는 프로세스 클래스 생성
// 2. 우선순위 배열 내림 차순 정렬
// 3. 프로세스 정보를 담고 있는 배열을 계속해서 순회하며 현재 가리키고 있는 우선순위와 일치하는지 체크

class Process {
    constructor(priority){
        this.priority = priority; // priority: 우선순위
        this.isProcessed = false; // isProcessed: 프로세스 처리 여부
    }
}

function solution(priorities, location) {
    var answer = 0;
    
    // processes: 프로세스 정보를 담고 있는 배열
    const processes = Array(priorities.length).fill(0).map((it, idx) => 
        new Process(priorities[idx])
    );
    
    // 우선순위 배열 내림차순 정렬
    priorities.sort((a, b) => b - a);
    
    let curPriorityIdx = 0;
    
    // idx: 프로세스 배열을 순회하기 위해 필요한 인덱스
    let idx = 0;
    
    while (curPriorityIdx < priorities.length){
        // curPriority: 현재 상황에서 찾아야 할 우선순위
        let curPriority = priorities[curPriorityIdx];
        
        // curProcess: 체크 대상 프로세스
        let curProcess = processes[idx];
        
        // 우선순위가 일치하고 아직 처리되지 않은 프로세스라면
        if (curProcess.priority === curPriority && !curProcess.isProcessed){
            
            // 프로세스 처리 상태 변경
            curProcess.isProcessed = true;
            
            // 우선순위 변경
            curPriorityIdx++;
            
            // 처리된 프로세스 수 증가
            answer++;
            
            if (location === idx) {
                break;
            }
        }
        
        idx = (idx + 1) % priorities.length;
    }
    
    
    return answer;
}