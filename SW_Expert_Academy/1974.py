import sys
sys.stdin = open("input.txt", "r")

test_case_count = int(input())

for i in range(1, test_case_count + 1):
    graph = list()
    for j in range(9):
        row = list(map(int, input().split()))
        graph.append(row)
    is_wrong = False
    for p in range(9):
        if (sum(graph[p]) != 45):
            is_wrong = True
            break
        sum_col = 0
        for u in range(9):
            sum_col += graph[u][p]
        if (sum_col != 45):
            is_wrong = True
            break


    for q in range(0, 9, 3):
        for r in range(0, 9, 3):
            sum_box = 0
            for s in range(0, 3, 1):
                for t in range(0, 3, 1):
                    sum_box += graph[q + s][r + t]
            if sum_box != 45:
                is_wrong = True

    if is_wrong:
        print(f"#{i} 0")
    else:
        print(f"#{i} 1")

