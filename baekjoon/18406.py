N = input()
left = 0
right = 0
length = len(N)
for i in range(length):
    if i < length //2:
        left += int(N[i])
    else:
        right += int(N[i])
if left == right:
    print('LUCKY')
else:
    print('READY')