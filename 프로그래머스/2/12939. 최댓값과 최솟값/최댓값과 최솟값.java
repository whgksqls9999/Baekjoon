class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] arr = s.split(" ");
        int[] intArr = new int[arr.length];
        
        for (int i = 0; i < intArr.length; i++){
            intArr[i] = Integer.parseInt(arr[i]);
        }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int i : intArr){
            if (i > max){
                max = i;
            }
            
            if (i < min){
                min = i;
            }
        }
        
        answer = min + " " + max;
        
        return answer;
    }
}