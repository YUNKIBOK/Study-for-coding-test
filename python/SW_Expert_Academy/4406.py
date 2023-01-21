import sys

sys.stdin = open("input.txt", "r")
T = int(input())
for t in range(1, T+1):
    str = input().strip()
    str = str.replace("a", "")
    str = str.replace("e", "")
    str = str.replace("i", "")
    str = str.replace("o", "")
    str = str.replace("u", "")
    print(f'#{t} {str}')