import sys
sys.stdin = open("input.txt", "r")

def turn_right(array):
    length = len(array[0])
    result = [[0] * length for _ in range(length)]
    for x in range(length):
        for y in range(length):
            result[y][length - 1 - x] = array[x][y]
    return result

test_case_count = int(input())
for t in range(test_case_count):
    n = int(input())
    numbers = list()
    for _ in range(n):
        row = list(input().split())
        numbers.append(row)
    turn_one = turn_right(numbers)
    turn_two = turn_right(turn_one)
    turn_three = turn_right(turn_two)
    print(f"#{t + 1}")
    for i in range(n):
        print("".join(turn_one[i]), end = " ")
        print("".join(turn_two[i]), end = " ")
        print("".join(turn_three[i]))
