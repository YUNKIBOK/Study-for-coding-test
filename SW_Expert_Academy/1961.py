import sys
sys.stdin = open("input.txt", "r")

def turn_right(array):
    length = len(array[0])
    result = [[0] * length for _ in range(length)]
    for i in range(length):
        for j in range(length):
            result[j][length - 1 - i] = array[i][j]
    return result

T = int(input())
for t in range(1, T + 1):
    N = int(input())
    original = list()
    for n in range(N):
        original.append(list(map(int, input().split())))
    one_right = turn_right(original)
    two_right = turn_right(one_right)
    three_right = turn_right(two_right)
    print(f"#{t}")
    for p in range(N):
        for q in range(N):
            print(one_right[p][q], end = "")
        print(end = " ")
        for q in range(N):
            print(two_right[p][q], end="")
        print(end=" ")
        for q in range(N):
            print(three_right[p][q], end = "")
        print()