import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes){
            String type = cloth[1];
            String name = cloth[0];
            
            if(map.containsKey(type)){
                map.put(type, map.get(type)+1);
            } else {
                map.put(type, 1);
            }
        }
        
        for (String key : map.keySet()){
            int cnt = map.get(key);
            answer *= cnt + 1;
        }
        
        return answer - 1;
    }
}