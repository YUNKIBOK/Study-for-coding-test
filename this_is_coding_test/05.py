N, M = map(int, input().split())
balls = list(map(int, input().split()))
total_count = len(balls)
result = 0
for weight in range(M, 1, -1):
    first_select = balls.count(weight)
    total_count -= first_select
    result += first_select * total_count
print(result)

