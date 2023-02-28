import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 부분조합을 이용해서 지역구를 분류한다(0과 1로 나눈다)
 * 분류된 지역구들을 탐색하여 연결되어 있는지 확인한다
 * 두 지역구 집합 모두 연결된 경우 인구 차이의 최솟값을 갱신한다
 * 인접한 구역이 없을 수도 있다
 */

public class Main {

	static int N;
	static int minDiff; // 지역구 인구 차이 최소 값
	static int calDiffCount; // 시뮬레이션 해야하는 횟수
	static int[] populations;
	static int[] parents;
	static boolean[][] graph;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		populations = new int[N + 1];
		graph = new boolean[N + 1][N + 1];
		calDiffCount = ((int) Math.pow(2, N)) / 2; // 부분 조합에서 {A, B}, {C}와 {C}, {A,B}가 같기 때문에 (2 ^ N) / 2번만 시뮬레이션한다
		minDiff = Integer.MAX_VALUE;

		// 인구 수 저장
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}

		// 지역구 연결 그래프 저장(인접 행렬)
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int adjCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt; j++) {
				int o = Integer.parseInt(st.nextToken());
				graph[i][o] = true;
				graph[o][i] = true;
			}
		}

		// 부분 조합 후 선거구 나누어 시뮬레이션하기
		powerSet(new boolean[N], 0);
	}

	static void powerSet(boolean[] selected, int count) {
		if (count >= N) {
//			System.out.println(Arrays.toString(selected));

			// 시뮬레이션 횟수를 모두 소진한 경우 출력하고 프로그램을 종료한다
			if (calDiffCount == 0) {
				if (minDiff == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else {
					System.out.println(minDiff);
				}
				System.exit(0);
			}

			calDiffCount--; // 시뮬레이션 횟수 소진

			// 부모 정보 초기화
			parents = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				parents[i] = i;
			}

			// 선거구 원소 개수 카운트
			int group1Cnt = 0;
			int group2Cnt = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i] == true) {
					group1Cnt++;
				} else {
					group2Cnt++;
				}
			}

			// 한 선거구는 적어도 하나의 구역을 포함해야 하므로 아래 경우는 종료한다
			if (group1Cnt == 0 || group2Cnt == 0) {
				return;
			}

			// 선거구 구성과 인구 수 계산
			int[] group1 = new int[group1Cnt];
			int[] group2 = new int[group2Cnt];
			group1Cnt = 0;
			group2Cnt = 0;
			int population1 = 0;
			int population2 = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i] == true) {
					group1[group1Cnt++] = i + 1;
					population1 += populations[i + 1];
				} else {
					group2[group2Cnt++] = i + 1;
					population2 += populations[i + 1];
				}
			}
//			System.out.println(Arrays.toString(group1));
//			System.out.println(Arrays.toString(group2));

			// 첫 번째 선거구 지역구 연결 검사
			for (int i = 0; i < group1.length; i++) {
				for (int j = 0; j < group1.length; j++) {
					if (i != j) {
						union(group1[i], group1[j]);
					}
				}
			}

			// 두 번째 선거구 지역구 연결 검사
			for (int i = 0; i < group2.length; i++) {
				for (int j = 0; j < group2.length; j++) {
					if (i != j) {
						union(group2[i], group2[j]);
					}
				}
			}

			// 모든 선거구의 지역구 연결 후 중복을 제거한 부모 개수 세기
			Set<Integer> existParents = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				existParents.add(findParent(i));
			}
//			System.out.println(Arrays.toString(existParents.toArray()));

			// 부모 개수가 두 개라면 선거구 시뮬레이션에 성공한 것이다
			if (existParents.size() == 2) {
				minDiff = Math.min(minDiff, Math.abs(population1 - population2));
			}

			return;
		}

		// 부분 조합에서 뽑은 경우
		selected[count] = true;
		powerSet(selected, count + 1);

		// 부분 조합에서 뽑지 않은 경우
		selected[count] = false;
		powerSet(selected, count + 1);
	}

	static int findParent(int v) {
		if (parents[v] == v)
			return v;
		return parents[v] = findParent(parents[v]);
	}

	static boolean union(int v1, int v2) {
		int p1 = findParent(v1);
		int p2 = findParent(v2);
		// 다른 집합에 속하고 그래프 상으로 연결된 경우만 집합을 합친다
		if (p1 == p2 || graph[v1][v2] == false) {
			return false;
		}
		if (p1 > p2) {
			parents[p1] = p2;
		} else {
			parents[p2] = p1;
		}
		return true;
	}

}