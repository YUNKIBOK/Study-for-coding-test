import sys
sys.stdin = open("input.txt", "r")

T = int(input())
count = 0
for t in range(1, T + 1):
    print(f"#{t}")
    N = int(input())
    for n in range(N):
        alphabet, num = input().split()
        num = int(num)
        for i in range(num):
            print(alphabet, end ='')
            count += 1
            if count == 10:
                print()
                count = 0
    count = 0
    print()