import sys

sys.stdin = open("input.txt", "r")
test_case_count = int(input())
for t in range(1, test_case_count + 1):
    N = int(input())
    numbers = list(map(int, input().split()))
    avg = sum(numbers) / N
    count = 0
    for n in numbers:
        if n <= avg:
            count += 1
    print(f'#{t} {count}')
