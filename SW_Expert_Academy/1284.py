import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for t in range(1, T + 1):
    P, Q, R, S, W = map(int, input().split())
    one = P * W
    two = Q + (W - R) * S if W > R else Q
    print(f"#{t} {min(one, two)}")