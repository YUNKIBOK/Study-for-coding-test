import sys
sys.stdin = open("input.txt", "r")

grades = "A+,A0,A-,B+,B0,B-,C+,C0,C-,D0"
grades = list(grades.split(','))
#print(grades)
T = int(input())
for i in range(1, T + 1):
    N, K = map(int, input().split())
    students = list()
    for j in range(1, N + 1):
        mid, final, homework = map(float, input().split())
        #print(mid, final, homework)
        students.append((mid * 0.35 + final * 0.45 + homework * 0.2, j))
    students.sort(reverse=True)
    # for s in students:
    #     print(s)
    possible = N // 10
    current = 0
    done = 0
    for s in range(N):
        score, num = students[s]
        grade = grades[current]
        students[s] = (num, grade, score)
        done += 1
        if done == possible:
            current += 1
            done = 0
    # for s in students:
    #     print(s)
    students.sort()
    print(f"#{i} {students[K - 1][1]}")