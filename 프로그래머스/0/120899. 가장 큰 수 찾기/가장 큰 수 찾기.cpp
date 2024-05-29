#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> array) {
    vector<int> answer = {-1, -1} ;
    
    for (int i = 0; i < array.size(); i++){
        if (array[i] > answer[0]){
            answer = {array[i], i};
        }
    }
    
    return answer;
}