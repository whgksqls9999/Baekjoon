import java.util.Queue;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        
        for (int i : scoville){
            pq.add(i);
        }
        
        for (;pq.size() > 1 && pq.peek() < K; ++answer){
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + b * 2);
        }
        
        if(pq.peek() < K){
            answer = -1;
        }
        
        return answer;
    }
}