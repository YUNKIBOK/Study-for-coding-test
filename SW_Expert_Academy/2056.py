import sys
sys.stdin = open("input.txt", "r")

day_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
n = int(input())
for i in range(1, n + 1):
    numbers = input()
    year = numbers[:4]
    month = numbers[4:6]
    day = numbers[6:]
    if (1 <= int(month) <= 12):
        if (1 <= int(day) <= day_in_month[int(month) - 1]):
            print(f"#{i} {year}/{month}/{day}")
            continue
    print(f"#{i} {-1}")