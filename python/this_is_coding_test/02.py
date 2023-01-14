S = input()
print(S)

result = int(S[0])
for i in range(1, len(S)):
    plus_result = result + int(S[i])
    mul_result = result * int(S[i])
    if plus_result > mul_result:
        result = plus_result
    else:
        result = mul_result
print(result)