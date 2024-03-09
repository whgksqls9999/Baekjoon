class Solution {
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        int answer = DFS(begin, target, words, 0);
        
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public int DFS(String cur, String target, String[] words, int depth){
        if (cur.equals(target)){
            return depth;
        }
        
        int res = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++){
            if (visited[i]) continue;
            
            String tmpWords = words[i];
            
            int sameCheck = 0;
            for (int j = 0; j < cur.length(); j++){
                if(cur.charAt(j) == tmpWords.charAt(j)){
                    sameCheck++;
                }
            }
            
            if (sameCheck == cur.length() - 1){
                visited[i] = true;
                res = Math.min(res, DFS(tmpWords, target, words, depth + 1));
                visited[i] = false;
            }
        }
        
        return res;
    }
}