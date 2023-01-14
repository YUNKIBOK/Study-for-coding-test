N = int(input())
board = [[0] * (N + 1) for _ in range(N + 1)]
K = int(input())
apple = dict()
for _ in range(K):
    apple_x, apple_y = map(int, input().split())
    apple[(apple_x, apple_y)]=1
L = int(input())
movement = dict()
for _ in range(L):
    time, turn = input().split()
    movement[int(time)] = turn

for i in board:
    print(i)
print(apple)
print(movement)

snake_x = 1
snake_y = 1

dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]
direction = 0
while True:
