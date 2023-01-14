N = int(input())
coins = list(map(int, input().split()))
coins.sort(reverse = 1)

max_value = sum(coins)
for i in range(1, max_value + 1):
    value = i
    for coin in coins:
        if value - coin >= 0:
            value -= coin
        elif value == 0:
            break
        else:
            continue
    if value > 0:
        print(i)
        break
