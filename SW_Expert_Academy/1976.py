import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for t in range(1, T + 1):
    one_hour, one_minute, two_hour, two_minute = map(int, input().split())
    hour = one_hour + two_hour
    minute = one_minute + two_minute
    if minute >= 60:
        minute -= 60
        hour += 1
    if hour > 12:
        hour -= 12
    print(f"#{t} {hour} {minute}")