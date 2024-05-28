#include <string>
#include <vector>

using namespace std;

bool isNumber(char ch){
    return ch >= '0' && ch <= '9';
}

int solution(string my_string) {
    int answer = 0;
    
    for (int i = 0; i < my_string.length(); i++){
        char cur = my_string.at(i);
        
        if (isNumber(cur)){
            answer += cur - '0';
        }
    }
    
    return answer;
}

