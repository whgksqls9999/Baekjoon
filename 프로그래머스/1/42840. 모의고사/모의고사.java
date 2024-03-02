import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] solution(int[] answers) {        
        int[][] cnt = new int[3][2];
        for (int i = 0; i < cnt.length; i++){
            cnt[i][0] = i+1;
        }
        
        for (int i = 0; i < answers.length; i++){
            if(answers[i] == i % 5 + 1){
                cnt[0][1]++;
            }
            
            if (i % 2 == 0){
                if (answers[i] == 2){
                    cnt[1][1]++;
                }
            } else {
                if(i % 8 == 1 && answers[i] == 1){
                    cnt[1][1]++;
                } else if (i % 8 == 3 && answers[i] == 3){
                    cnt[1][1]++;
                } else if (i % 8 == 5 && answers[i] == 4){
                    cnt[1][1]++;
                } else if (i % 8 == 7 && answers[i] == 5){
                    cnt[1][1]++;
                }          
            }
            
            if (i % 10 <= 1){
                if (answers[i] == 3)
                    cnt[2][1]++;
            } else if (i % 10 <= 3){
                if (answers[i] == 1)
                    cnt[2][1]++;
            } else if (i % 10 <= 5){
                if (answers[i] == 2)
                    cnt[2][1]++;
            } else if (i % 10 <= 7){
                if (answers[i] == 4)
                    cnt[2][1]++;
            } else if (i % 10 <= 9){
                if (answers[i] == 5)
                    cnt[2][1]++;
            }
        }
        
        Arrays.sort(cnt, new Comparator<int[]>(){
            
            @Override
            public int compare (int[] o1, int[] o2){
                if (o1[1] != o2[1]){
                    return o2[1] - o1[1];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        
        
        int length = 0;
        int max = 0;
        for (int[] i : cnt){
            max = Math.max(max, i[1]);
        }
        
        for (int[] i : cnt){
            if (max == i[1]) length++;
        }
        
        int[] answer = new int[length];
        
        for (int i = 0; i < length; i++){
            answer[i] = cnt[i][0];
        }
        

        return answer;
    }
}