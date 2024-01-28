import java.util.List;
import java.util.ArrayList;

class Solution {
    List<Integer>[] tree;
    int[] arr;
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        
        arr = array;
        tree = new ArrayList[array.length * 4];
        for (int i = 1; i < tree.length; i++){
            tree[i] = new ArrayList<>();
        }
        init(1,1,array.length);
        
        int idx = 0;
        for (int[] command : commands){
            int l = command[0];
            int r = command[1];
            int k = command[2];
            int s = 1, e = 100;
            
            while(s <= e){
                int mid = (s+e)/2;    
                int res = query(1,1,arr.length, l, r, mid);
                
                if(res < k){
                    s = mid + 1;    
                } else {
                    e = mid - 1;
                }
            }
            
            answer[idx++] = e;
        }
        
        return answer;
    }
    
    public void init(int node, int start, int end){
        if (start == end){
            tree[node].add(arr[start-1]);
            return;
        }
        
        int mid = (start+end)/2;
        init(node*2, start, mid);
        init(node*2+1, mid+1, end);
        sort(node, tree[node*2], tree[node*2+1]);
    }
    
    public void sort (int node, List<Integer> left, List<Integer> right){
        int l = 0, r = 0;
        List<Integer> tmp = new ArrayList<>();
        
        while (l < left.size() && r < right.size()){
            if (left.get(l) > right.get(r)){
                tmp.add(right.get(r++));
            } else {
                tmp.add(left.get(l++));
            }
        }
        
        while (l < left.size()){
            tmp.add(left.get(l++));
        }
        
        while (r < right.size()){
            tmp.add(right.get(r++));
        }
        
        tree[node] = tmp;
    }
    
    public int query(int node, int start, int end, int left, int right, int val){
        if (end < left || right < start){
            return 0;
        }
        
        if (left <= start && end <= right){
            int s = 0;
            int e = tree[node].size();

            while(s < e){
                int mid = (s+e)/2;
                
                if(tree[node].get(mid) < val){
                    s = mid + 1;
                } else {
                    e = mid;
                }
            }
            return e;
        }
        
        int mid = (start + end)/2;
        int l = query(node * 2, start, mid, left, right, val);
        int r = query(node * 2 + 1, mid+1, end, left, right, val);
        return l+r;
    }
}