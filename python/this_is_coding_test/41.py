import sys
input = sys.stdin.readline

N, M = map(int, input().split())
parent = [0] * (N)
graph = []

for _ in range(N):
    temp = list(map(int, input().split()))
    graph.append(temp)

plan = list(map(int, input().split()))

for i in range(N):
    parent[i] = i

def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

for i in range(N):
    for j in range(N):
        if graph[i][j] == 1 and find_parent(parent, i) != find_parent(parent, j):
            union_parent(parent, i, j)

result = 'YES'
for k in range(M - 1):
    if find_parent(parent, plan[k] - 1) != find_parent(parent, plan[k + 1] - 1):
        result = 'NO'
        break
print(result)
