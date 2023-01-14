import sys
sys.stdin = open("input.txt", "r")

test_case_count = int(input())

for i in range(1, test_case_count + 1):
    N, K = map(int, input().split())
    graph = list()
    for j in range(N):
        graph.append(list(map(int, input().split())))

    answer = 0
    for p in range(N):
        for q in range(N):
            if graph[p][q] == 1:
                if 0 <= p + (K - 1) < N:
                    is_possible = True
                    if 0 <= p - 1 and graph[p - 1][q] == 1:
                        is_possible = False
                    if p + K < N and graph[p + K][q] == 1:
                        is_possible = False

                    for r in range(1, K):
                        if graph[p + r][q] == 0:
                            is_possible = False
                            break
                    if is_possible:
                        answer += 1
                if 0 <= q + (K - 1) < N:
                    is_possible = True
                    if 0 <= q - 1 and graph[p][q - 1] == 1:
                        is_possible = False
                    if q + K < N and graph[p][q + K] == 1:
                        is_possible = False

                    for r in range(1, K):
                        if graph[p][q + r] == 0:
                            is_possible = False
                            break
                    if is_possible:
                        answer += 1
    print(f"#{i} {answer}")