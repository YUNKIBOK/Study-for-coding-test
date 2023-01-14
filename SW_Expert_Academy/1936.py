import sys
sys.stdin = open("input.txt", "r")

one, two = map(int, input().split())
if (abs(one - two) == 2):
    result = "A" if one < two else "B"
else:
    result = "A" if one > two else "B"
print(result)