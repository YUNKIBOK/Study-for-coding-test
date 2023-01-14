import sys
input = sys.stdin.readline
G = int(input())
P = int(input())
parent = [0] * (G + 1)
flights=list()
for i in range(G + 1):
    parent[i] = i

def find_parent(parnet, x):
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

for _ in range(P):
    flights.append(int(input()))

count = 0
for flight in flights:
    p = find_parent(parent, flight)
    if p == 0:
        break
    else:
        union_parent(parent, p, p - 1)
        count += 1

print(count)