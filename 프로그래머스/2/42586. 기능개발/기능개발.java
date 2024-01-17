import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {    
        Queue<int[]> queue = new ArrayDeque<>();
        
        for(int i = 0; i < progresses.length; ++i){
            queue.add(new int[]{progresses[i], speeds[i]});
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int days = 0;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cnt = 0;
            int tmp = (int) Math.ceil(((100 - (cur[0] + days * cur[1])) / (double)cur[1]));
            days += tmp;
            ++cnt;
            
            while(!queue.isEmpty()){
                int[] now = queue.peek();
                if(now[0] + now[1] * days >= 100){
                    ++cnt;
                    queue.poll();
                } else {
                    break;
                }
            }
            
            list.add(cnt);
        }
    
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}