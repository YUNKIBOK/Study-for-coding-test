import sys
input = sys.stdin.readline
N = int(input())
x = []
y = []
z = []
edges=[]
parent = [i for i in range(N + 1)]

for i in range(N):
    x1, y1, z1 = map(int, input().split())
    x.append((x1, i + 1))
    y.append((y1, i + 1))
    z.append((z1, i + 1))
x.sort()
y.sort()
z.sort()

for i in range(N - 1):
    edges.append((abs(x[i][0] - x[i + 1][0]), x[i][1], x[i + 1][1]))
    edges.append((abs(y[i][0] - y[i + 1][0]), y[i][1], y[i + 1][1]))
    edges.append((abs(z[i][0] - z[i + 1][0]), z[i][1], z[i + 1][1]))

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

total_cost = 0
for edge in edges:
    cost, a, b = edge
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        total_cost += cost

print(total_cost)
