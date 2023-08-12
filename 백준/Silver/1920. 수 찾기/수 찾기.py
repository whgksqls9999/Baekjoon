N = int(input())
X = list(map(int,input().split()))

M = int(input())
Y = list(map(int,input().split()))

C = set(X).intersection(set(Y))
for i in Y:
    if i in C:
        print(1)
    else:
        print(0)
