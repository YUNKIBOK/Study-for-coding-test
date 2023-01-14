import sys
sys.stdin = open("input.txt", "r")

def find_max_val(long, short):
    l = len(long)
    s = len(short)
    max_val = 0
    for i in range(l - s + 1):
        sum = 0
        for j in range(s):
            sum += long[i + j] * short[j]
        if sum > max_val:
            max_val = sum
    return max_val

test_case_count = int(input())
for t in range(test_case_count):
    n, m = map(int, input().split())
    a_array = list(map(int, input().split()))
    b_array = list(map(int, input().split()))
    result = find_max_val(a_array, b_array) if n > m else find_max_val(b_array, a_array)
    print(f"#{t + 1} {result}")
