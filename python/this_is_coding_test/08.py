S = list(input())
sum = 0
result=[]
for letter in S:
    if letter.isdigit():
        sum+=int(letter)
    else:
        result.append(letter)
result.sort()
for i in result:
    print(i, end='')
print(sum)