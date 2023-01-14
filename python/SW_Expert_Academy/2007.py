import sys
sys.stdin = open("input.txt", "r")

test_case_count = int(input())
for i in range(1, test_case_count + 1):
    string = input()
    for j in range(1, 10 + 1):
        one = string[ : j]
        two = string[j : 2 * j]
        if one == two:
            print(f"#{i} {j}")
            break