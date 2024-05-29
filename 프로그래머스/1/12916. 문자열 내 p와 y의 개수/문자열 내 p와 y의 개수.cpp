#include <string>
#include <iostream>
using namespace std;

bool solution(string s)
{
    bool answer = true;

    int p = 0;
    int y = 0;
    
    for (char c : s){
        c = tolower(c);
        if (c == 'p'){
            p++;
        } else if (c == 'y'){
            y++;
        }
    }
    
    answer = p == y;
    return answer;
}