import sys

sys.stdin = open("input.txt", "r")
T = int(input())
for t in range(1, T+1):
    before_bit = "0"
    count = 0
    bits = input().strip()
    for bit in bits:

        if bit != before_bit:
            count += 1
            before_bit = bit
    print(f'#{t} {count}')
