S = input()

zero_count = 0
one_count = 0
for i in range(1, len(S)):
    if S[i] != S[i - 1] and S[i] == '1':
        zero_count += 1
    elif S[i] != S[i - 1] and S[i] == '0':
        one_count += 1
if S[-1] == '1':
    one_count += 1
else:
    zero_count += 1
print(min(one_count, zero_count))
