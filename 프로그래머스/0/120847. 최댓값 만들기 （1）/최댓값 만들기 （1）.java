import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        int result = 1;

        for (int i = 0; i < 2; i++){
            result *= numbers[numbers.length - 1 - i];
        }

        return result;
    }
}