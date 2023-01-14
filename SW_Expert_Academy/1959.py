import sys
sys.stdin = open("input.txt", "r")

def compare(long, short):
    max_sum = 0
    for i in range(len(long) - len(short) + 1):
        sum = 0
        for j in range(len(short)):
            sum += long[i + j] * short[j]
        max_sum = max(max_sum, sum)
    return max_sum

T = int(input())
for t in range(1, T + 1):
    N, M = map(int, input().split())
    one = list(map(int, input().split()))
    two = list(map(int, input().split()))
    result = compare(one, two) if N > M else compare(two, one)
    print(f"#{t} {result}")




