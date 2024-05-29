#include <string>
#include <vector>
#include <cctype>

using namespace std;

string solution(string my_string) {
    string answer = "";
    
    for (int i = 0; i < my_string.length(); i++){
        char ch = my_string.at(i);
        
        if (ch >= 'a' && ch <= 'z'){
            answer += toupper(ch);
        } else {
            answer += tolower(ch);
        }
    }
    
    return answer;
}