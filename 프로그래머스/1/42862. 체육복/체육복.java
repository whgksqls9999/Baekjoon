class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] arr = new int[n+1];
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = 1;
        }
        
        for (int i : lost){
            arr[i]--;
        }
        
        for (int i : reserve){
            arr[i]++;
        }
        
        for (int i = 1; i < arr.length; i++){
            if (arr[i] == 0){
                for (int j = -1; j <= 1; j += 2){
                    if (i+j > 0 && i+j < arr.length && arr[i+j] > 1){
                        arr[i]++;
                        arr[i+j]--;
                        break;
                    }
                }
            }
            if (arr[i] > 0){
                ++answer;
            }
        }
        return answer;
    }
}