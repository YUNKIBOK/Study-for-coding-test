import sys
sys.stdin = open("input.txt", "r")

test_case_count = 10
for t in range(test_case_count):
    test_case_num = int(input())
    sums = list()
    numbers = list()
    for i in range(100):
        row = list(map(int, input().split()))
        sums.append(sum(row))
        numbers.append(row)
    right_up_sum = 0
    left_down_sum = 0
    for col in range(100):
        col_sum = 0
        for row in range(100):
            if col == row:
                left_down_sum += numbers[row][col]
            if (col + row) == 100 - 1:
                right_up_sum += numbers[row][col]
            col_sum += numbers[row][col]
        sums.append(col_sum)
    sums.append(right_up_sum)
    sums.append(left_down_sum)
    print(f"#{test_case_num} {max(sums)}")