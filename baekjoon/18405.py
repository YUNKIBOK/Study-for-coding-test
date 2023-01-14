import sys
from collections import deque
input = sys.stdin.readline
N, K = map(int, input().split())
graph = []
q = []

for _ in range(N):
    row = list(map(int, input().split()))
    graph.append(row)
S, X, Y = map(int, input().split())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for i in range(N):
    for j in range(N):
        if graph[i][j] != 0:
            q.append((graph[i][j], 0, i, j))

q.sort()
q=deque(q)
while q:
    virus, time, x, y = q.popleft()
    if time == S:
        break
    for direction in range(4):
        nx = x + dx[direction]
        ny = y + dy[direction]
        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue

        if graph[nx][ny] == 0:
            graph[nx][ny] = virus
            q.append((virus, time + 1, nx, ny))

print(graph[X - 1][Y - 1])
