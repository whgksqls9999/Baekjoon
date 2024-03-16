import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        int LIMIT = 9999999;
        int[] arr = new int[LIMIT+1];
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
        arr[1] = -1;
        for (int i = 2; i <= Math.sqrt(LIMIT); i++){
            if (arr[i] == i){
                for (int j = i + i; j <= LIMIT; j+=i){
                    arr[j] = -1;
                }    
            }       
        }
        
        for (int i = 1; i <= numbers.length(); i++){
            DFS(0, i, arr, numbers, "", set, new boolean[numbers.length()]);
        }
        
        answer = set.size();

        return answer;
    }
    
    public void DFS(int sel, int limit, int[] arr, String string, String intStr, Set<Integer> set, boolean[] used){
        if (sel == limit){
            int cur = Integer.parseInt(intStr);

            if (arr[cur] != -1){
                set.add(cur);    
            }
            return;
        }
        
        for (int i = 0; i < string.length(); i++){
            if (sel == 0 && string.charAt(i) == '0') continue;
            if (used[i]) continue;
            
            used[i] = true;
            DFS(sel + 1, limit, arr, string, intStr + Character.toString(string.charAt(i)), set, used);
            used[i] = false;
        }
    }
}