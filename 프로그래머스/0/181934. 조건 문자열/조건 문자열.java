class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        String comparer = this.getComparer(ineq, eq);        
        
        if (comparer.equals(">=")) {
            if (n >= m) return 1;
        } else if (comparer.equals("<=")){
            if (n <= m) return 1;
        } else if (comparer.equals(">")){
            if (n > m) return 1;
        } else if (comparer.equals("<")){
            if (n < m) return 1;
        }
        
        return 0;
    }
    
    private String getComparer(String ineq, String eq){
        String result = ineq;
    
        if (!eq.equals("!")){
            result += "=";
        }
        
        return result;
    }
}
