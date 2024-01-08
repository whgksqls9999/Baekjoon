import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {      
        Map<String, String> map = new HashMap<>();
        
        int output = 0;
        for (String s : record){
            String[] input = s.split(" ");
            
            if (input[0].equals("Enter") || input[0].equals("Change")){
                map.put(input[1], input[2]);
            }
            
            if(input[0].equals("Enter") || input[0].equals("Leave")){
                ++output;
            }
        }
        
        String[] answer = new String[output];
        
        int idx = 0;
        for (int i = 0; i < record.length; ++i){
            String[] input = record[i].split(" ");
            
            if (input[0].equals("Enter")){
                answer[idx++] = map.get(input[1]) + "님이 들어왔습니다.";
            } else if (input[0].equals("Leave")){
                answer[idx++] = map.get(input[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
    
}