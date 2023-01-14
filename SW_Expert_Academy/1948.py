import sys
sys.stdin = open("input.txt", "r")

days_in_month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
T = int(input())
for t in range(1, T + 1):
    print(f"#{t} ", end = '')
    first_month, first_day, second_month, second_day = map(int, input().split())
    if first_month == second_month:
        print(second_day - first_day + 1)
    else:
        result = days_in_month[first_month] - first_day + 1
        for i in range(first_month + 1, second_month):
            result += days_in_month[i]
        result += second_day
        print(result)
