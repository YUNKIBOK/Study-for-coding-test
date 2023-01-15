import sys
sys.stdin = open('input.txt', 'r')

test_case_count = int(input())
for t in range(1, test_case_count + 1):
    A, B = map(int, input().split())
    result = A + B
    while (result >= 24):
        result %= 24
    print(f'#{t} {result}')