import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for t in range(1, T + 1):
    numbers = list(map(int, input().split()))
    numbers.sort()
    average = round(sum(numbers[1 : -1]) / 8)
    print(f"#{t} {average}")
