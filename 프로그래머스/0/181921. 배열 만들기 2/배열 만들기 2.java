import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
                List<Integer> list = new ArrayList<>();

        outer: for (int i = l; i <= r; ++i){
            String str = Integer.toString(i);

            for (char c : str.toCharArray()){
                int num = c - '0';
                if (num != 5 && num != 0){
                    continue outer;
                }
            }

            list.add(i);
        }

        int size = list.size();
        boolean flag = false;
        if (size == 0) {
            ++size;
            flag = true;
        }

        int[] result = new int[size];

        if (flag){
            result[0] = -1;
        } else {
            for (int i = 0; i < list.size(); ++i) {
                result[i] = list.get(i);
            }

            Arrays.sort(result);
        }

        return result;
    }
}