import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 주어진 순서에 따라 시뮬레이션한다
 * 2차원 배열에 미생물 군집 존재 여부를 나타내고
 * 리스트에 미생물 정보(좌표, 미생물 수, 방향)을 저장하고 미생물 수 내림차순 상태를 유지한다
 * 미생물 수가 많은 군집부터 이동시킨다
 * 이미 다른 군집이 존재하는 곳에 도착한다면 미생물 수를 합치지만 한다
 */

public class Solution {

	static int N, M, K;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static boolean[][] isExist; // 이미 다른 미생물 군집이 위치했는지 여부
	static List<Group> groups;
	static List<Group> tempGroups; // 시뮬레이션용

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			groups = new ArrayList<>();

			// 초기 미생물 군집 저장
			int r, c, size, direction;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				size = Integer.parseInt(st.nextToken());
				direction = Integer.parseInt(st.nextToken());
				groups.add(new Group(r, c, size, direction));

			}

			// 시뮬레이션
			for (int i = 0; i < M; i++) {
				Collections.sort(groups);
				isExist = new boolean[N][N];
				tempGroups = new ArrayList<>();

				int groupSize = groups.size();
				int nr, nc;
				for (int j = 0; j < groupSize; j++) {
					Group current = groups.get(j);
					nr = current.r + dr[current.direction];
					nc = current.c + dc[current.direction];
					// 약품이 칠해진 부분에 도달한 경우
					if (0 == nr || nr == N - 1 || 0 == nc || nc == N - 1) {
						current.size /= 2;
						if (current.size == 0) {
							continue;
						}
						current.direction++;
						if (current.direction == 3) {
							current.direction = 1;
						} else if (current.direction == 5) {
							current.direction = 3;
						}
					}
					// 다른 미생물 군집과 합쳐지는 경우
					if (isExist[nr][nc] == true) {
						for (int k = 0; k < tempGroups.size(); k++) {
							Group temp = tempGroups.get(k);
							if (temp.r == nr && temp.c == nc) {
								temp.size += current.size;
								break;
							}
						}
						continue;
					}
					// 단독으로 이동하는 경우
					isExist[nr][nc] = true;
					tempGroups.add(new Group(nr, nc, current.size, current.direction));
				}

				groups = tempGroups;
			}

			// 남아 있는 미생물 수의 총합 구하기
			int sum = 0;
			for (int i = 0; i < tempGroups.size(); i++) {
				sum += tempGroups.get(i).size;
			}
			sb.append(sum).append("\n");
		}

		System.out.println(sb.toString());
	}

}

class Group implements Comparable<Group> {

	int r, c, size, direction;

	public Group(int r, int c, int size, int direction) {
		this.r = r;
		this.c = c;
		this.size = size;
		this.direction = direction;
	}

	@Override
	public int compareTo(Group o) {
		return Integer.compare(o.size, this.size);
	}

}