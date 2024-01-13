import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = participant[0];
        
        // 완주자 명단을 set에 저장
        Map<String, Integer> map = new HashMap<>();
        
        for (String player : completion){
            int cnt = map.getOrDefault(player, 0);
            
            map.put(player, cnt+1);
        }

        // 참가자 명단과 완주자 명단을 대조
        for (String player : participant){
            
            int cnt = map.getOrDefault(player, 0);
            
            if(cnt == 0){
                answer = player;
                break;
            }
            
            map.put(player, cnt-1);
        }
        return answer;
    }
}