import sys
import heapq
INF=int(1e9)
input = sys.stdin.readline
N, M = map(int, input().split())
hidden_spots=[[] for _ in range(N+1)]
for _ in range(M):
    a, b = map(int, input().split())
    hidden_spots[a].append((b, 1))
    hidden_spots[b].append((a, 1))

distance=[INF]*(N+1)
start = 1
distance[start] = 0
q = list()
heapq.heappush(q, (distance[start],start))
while q:
    dist, now = heapq.heappop(q)
    if dist > distance[now]:
        continue
    for i in hidden_spots[now]:
        cost=dist+i[1]
        if cost < distance[i[0]]:
            distance[i[0]] = cost
            heapq.heappush(q, (cost, i[0]))

max=-1
for i in range(1, N+1):
    if max<distance[i]:
        max=distance[i]

print(distance.index(max),max,distance.count(max))


