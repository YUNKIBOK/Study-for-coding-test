import sys
sys.stdin = open("input.txt", "r")

def check(numbers):
    for x in range(9):
        if sum(numbers[x]) != 45:
            return 0
        col_sum = 0
        for y in range(9):
            col_sum += numbers[y][x]
        if col_sum != 45:
            return 0
    for i in range(0, 9, 3):
        for j in range(0, 9, 3):
            box_sum = 0
            for n in range(3):
                for m in range(3):
                    box_sum += numbers[i + n][j + m]
            if box_sum != 45:
                return 0
    return 1

test_case_count = int(input())
for t in range(test_case_count):
    numbers = list()
    for _ in range(9):
        row = list(map(int, input().split()))
        numbers.append(row)
    print(f"#{t + 1} {check(numbers)}")