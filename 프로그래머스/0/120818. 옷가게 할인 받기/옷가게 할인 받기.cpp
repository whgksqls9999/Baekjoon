#include <string>
#include <vector>
#include <cmath>

using namespace std;

int solution(int price) {
    int answer = 0;
    
    if (price >= 500000){
        answer = floor(price * 0.8);
    } else if (price >= 300000){
        answer = floor(price * 0.9);
    } else if (price >= 100000){
        answer = floor(price * 0.95);
    } else {
        answer = price;
    }
               
    return answer;
}