import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for t in range(1, T + 1):
    N = int(input())
    speed = 0
    result = 0
    for n in range(N):
        command = 0
        inputs = list(map(int, input().split()))
        if len(inputs) == 2:
            command, value = inputs
            if command == 1:
                speed += value

            else:
                speed -= value
                if speed < 0:
                    speed = 0
        result += speed
    print(f"#{t} {result}")