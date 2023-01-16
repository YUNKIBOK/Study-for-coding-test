import sys

sys.stdin = open("input.txt", "r")
test_case_count = int(input())
for t in range(1, test_case_count + 1):
    print(f"#{t} {int(input())//3}")