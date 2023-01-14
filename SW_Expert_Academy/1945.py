import sys
sys.stdin = open("input.txt", "r")

numbers = [2, 3, 5, 7, 11]

T = int(input())
for t in range(1, T + 1):
    N = int(input())
    results = [0, 0, 0, 0, 0]
    current = 0
    count = 0
    while N != 1:
        if N % numbers[current] == 0:
            N //= numbers[current]
            results[current] += 1
        else:
            current += 1
    print(f"#{t}", end = " ")
    for r in results:
        print(f"{r}", end = " ")
    print()