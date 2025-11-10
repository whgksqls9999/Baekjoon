import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(n);

        while (n != 1){
            if (isOdd(n)){
                n = 3 * n + 1;
            } else {
                n /= 2;
            }
            list.add(n);
        }
        
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; ++i){
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    private boolean isOdd(int n){
        return n % 2 != 0;
    }
}