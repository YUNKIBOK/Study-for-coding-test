import sys

sys.stdin = open("input.txt", "r")
test_case_count = int(input())
for t in range(1, test_case_count + 1):
    L, U, X = map(int, input().split())
    result = 0
    if X > U:
        result = -1
    elif X < L:
        result = L - X
    print(f"#{t} {result}")