import sys
sys.stdin = open("input.txt", "r")

moneys = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
T = int(input())
for t in range(1, T + 1):
    result = [0, 0, 0, 0, 0, 0, 0, 0]
    N = int(input())
    for m in range(len(moneys)):
        result[m] = N // moneys[m]
        N %= moneys[m]
    print(f"#{t} ", end = '')
    for m in range(len(result)):
        print(result[m], end =' ')
    print()