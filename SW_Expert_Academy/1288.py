import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for t in range(1, T + 1):
    N = int(input())
    numbers = set()
    count = 0
    now = 0
    while True:
        count += 1
        now += N
        string = str(now)
        for n in string:
            numbers.add(n)
        if len(numbers) == 10:
            break
    print(f"#{t} {count * N}")
