class Solution {
    public int solution(String number) {
        int answer = 0;
        
        for (char c : number.toCharArray()){
            answer += transformToNumber(c);
        }
        
        return answer % 9;
    }
    
    private int transformToNumber(char c){
        return c - '0';
    }
}