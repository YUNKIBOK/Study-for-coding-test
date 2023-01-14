import sys
input= sys.stdin.readline
N, M = map(int, input().split())
edges = []
parent = [i for i in range(N)]

total_cost = 0
for _ in range(M):
    a, b, cost = map(int, input().split())
    total_cost += cost
    edges.append((cost, a, b))

edges.sort()

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

min_cost = 0
for edge in edges:
    cost, a, b = edge
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        min_cost+=cost

print(total_cost - min_cost)
