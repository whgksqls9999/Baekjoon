T = int(input())
def GCD(A,B):
    r = A%B
    if r == 0:
        return B
    return GCD(B,r)
for i in range(T):
    A, B = map(int,input().split())
    c = GCD(A,B)
    print(A*B//c)