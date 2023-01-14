import sys
sys.stdin = open("input.txt", "r")

test_case_count = int(input())
for t in range(test_case_count):
    count_per_score = dict()
    test_case_num = int(input())
    scores = map(int, input().split())
    for s in scores:
        if s in count_per_score:
            count_per_score[s] += 1
        else:
            count_per_score[s] = 1
    max_key = 0
    max_value = 0
    keys = sorted(count_per_score.keys(), reverse = True)
    for k in keys:
        if max_value < count_per_score[k]:
            max_key = k
            max_value = count_per_score[k]
    print(f"#{test_case_num} {max_key}")