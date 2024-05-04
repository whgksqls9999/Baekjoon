import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a,b) -> b - a);
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((a,b) -> a - b);
        
        HashMap<Integer, Integer> maxMap = new HashMap();
        HashMap<Integer, Integer> minMap = new HashMap();
        
        for (String operation : operations){
            String[] command = operation.split(" ");
            
            int num = Integer.parseInt(command[1]);
            
            switch(command[0]){
                case "I":        
                    maxQueue.add(num);
                    minQueue.add(num);
                    break;
                case "D":
                    if (num > 0){
                        if (maxQueue.isEmpty()) break;
                        
                        int max = maxQueue.peek();
                        
                        while(minMap.getOrDefault(max, 0) != 0){                    
                            minMap.put(max, minMap.get(max) - 1);
                            maxQueue.poll();
                            
                            if (!maxQueue.isEmpty()){
                                max = maxQueue.peek();    
                            }
                        }
                        
                        maxMap.put(max, maxMap.getOrDefault(max, 0) + 1);           
                        if (!maxQueue.isEmpty() && max == maxQueue.peek()){
                            maxQueue.poll();
                        }
                        
                    } 
                    else if (num < 0){
                        if (minQueue.isEmpty()) break;
                        int min = minQueue.peek();
                        
                        while(maxMap.getOrDefault(min, 0) != 0){
                            maxMap.put(min, maxMap.get(min)-1);
                            minQueue.poll();
                            
                            if (!minQueue.isEmpty()){
                                min = minQueue.peek();    
                            }
                        }

                        minMap.put(min, minMap.getOrDefault(min, 0) + 1);
                        if (!minQueue.isEmpty()){
                            minQueue.poll();    
                        }
                    }
                    break;
            }
        }
        
        if (minQueue.isEmpty() && maxQueue.isEmpty()){
            answer = new int[]{0, 0};    
        } else {            
            while(!maxQueue.isEmpty() && minMap.getOrDefault(maxQueue.peek(), 0) != 0){
                maxQueue.poll();
            }
            
            while(!minQueue.isEmpty() && maxMap.getOrDefault(minQueue.peek(), 0) != 0) {
                minQueue.poll();
            }                  
                  
            int max = maxQueue.isEmpty() ? 0 : maxQueue.peek();
            int min = minQueue.isEmpty() ? 0 : minQueue.peek(); 
            
            answer = new int[]{max, min};
        }
        
        return answer;
    }
}