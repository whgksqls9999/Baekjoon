import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        
        int BASIC_TIME = fees[0];
        int BASIC_FEE = fees[1];
        int UNIT_TIME = fees[2];
        int UNIT_FEE = fees[3];
        
        Map<String, Integer> fee = new TreeMap<>();
        Map<String, List<Integer>> time = new HashMap<>();
        
        for (String s : records){
            String[] arr = s.split(" ");
            String[] tmpTime = arr[0].split(":");
            
            int curTime = Integer.parseInt(tmpTime[0]) * 60 + Integer.parseInt(tmpTime[1]);
            
            List<Integer> curTimeMap = time.getOrDefault(arr[1], new ArrayList<>());
            curTimeMap.add(curTime);
            time.put(arr[1], curTimeMap);
        }
        
        for (String key : time.keySet()){
            List<Integer> curList = time.get(key);
            if (curList.size() % 2 != 0){
                curList.add(23 * 60 + 59);
            }
            
            int curFee = 0;
            for (int i = 0; i < curList.size(); i++){
                if (i % 2 == 0){
                    curFee -= curList.get(i);
                } else {
                    curFee += curList.get(i);
                }
            }
            if (curFee <= BASIC_TIME){
                curFee = BASIC_FEE;
            } else {
                curFee = BASIC_FEE + (int)Math.ceil((double)(curFee - BASIC_TIME) / UNIT_TIME) * UNIT_FEE;
            }
            fee.put(key, curFee);
        }
        
        answer = new int[fee.size()];
        
        int idx = 0;
        for (String key : fee.keySet()){
            answer[idx++] = fee.get(key);
        }
        
        return answer;
    }
}