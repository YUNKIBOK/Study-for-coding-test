import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * N번의 턴이 종료되면 게임이 끝나게 되어 있다
 * MC3을 활용해 궁수 배치에 대한 조합을 만들고
 * 각 경우에 대해 시뮬레이션을 반복한다
 * 궁수는 가장 가까운 상대부터 공격한다
 * BFS를 사용하여 적을 찾는다
 * 적을 바로 공격하지 않고 Set에 넣어둔다
 * 모든 궁수가 공격 대상을 찾았을 때 중복을 제거한 뒤 공격한다
 */

public class Main {

	static int N;
	static int M;
	static int D;
	// 궁수의 열 위치(0열: 1번 궁수의 열 위치 ...)
	static List<int[]> combinations;
	static boolean[][] map;

	static int maxCount;
	static int tempCount;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// 게임 맵 저장
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = (Integer.parseInt(st.nextToken()) == 0) ? false : true;
			}
		}

		// 궁수의 위치에 대한 조합 생성
		combinations = new ArrayList<int[]>();
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					int[] combination = { i, j, k };
					combinations.add(combination);
				}
			}
		}

		// 조합에 대해 시뮬레이션 수행
		maxCount = Integer.MIN_VALUE;
		for (int[] hunters : combinations) {
			simulate(hunters);
			maxCount = Math.max(maxCount, tempCount);
		}

		System.out.println(maxCount);
	}

	static void simulate(int[] hunters) {
		tempCount = 0;

		// 임시 게임 맵 생성
		boolean[][] tempMap = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, M);
		}

		// N초 간 공격한다
		for (int s = 0; s < N; s++) {
			// 궁수별 공격할 적 찾기
			Set<String> targets = new HashSet<>();
			for (int hunterCol : hunters) {
				// BFS
				int[][] distance = new int[N][M];
				Queue<String> queue = new ArrayDeque<>();
				queue.add((N - 1) + " " + hunterCol);
				distance[N - 1][hunterCol] = 1;

				search: while (queue.size() > 0) {
					String current = queue.poll();
					StringTokenizer st = new StringTokenizer(current, " ");
					int currentR = Integer.parseInt(st.nextToken());
					int currentC = Integer.parseInt(st.nextToken());
					// 공격 거리 제한을 넘어가면 탐색을 중단한다
					if (distance[currentR][currentC] > D) {
						break search;
					}
					// 공격 가능한 적을 찾으면 공격 대상에 추가한다
					if (tempMap[currentR][currentC] == true) {
						targets.add(currentR + " " + currentC);
						break search;
					}
					// 왼쪽 탐색
					if (0 <= currentC - 1 && distance[currentR][currentC - 1] == 0) {
						queue.add(currentR + " " + (currentC - 1));
						distance[currentR][currentC - 1] = distance[currentR][currentC] + 1;
					}
					// 정면 탐색
					if (0 <= currentR - 1 && distance[currentR - 1][currentC] == 0) {
						queue.add((currentR - 1) + " " + (currentC));
						distance[currentR - 1][currentC] = distance[currentR][currentC] + 1;
					}
					// 오른쪽 탐색
					if (currentC + 1 < M && distance[currentR][currentC + 1] == 0) {
						queue.add(currentR + " " + (currentC + 1));
						distance[currentR][currentC + 1] = distance[currentR][currentC] + 1;
					}
				}
			}
			tempCount += targets.size();

			// 적 공격하기
			for (String target : targets) {
				StringTokenizer st = new StringTokenizer(target, " ");
				tempMap[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = false;
			}

			// 적 이동하기
			for (int i = N - 1; i > 0; i--) {
				tempMap[i] = tempMap[i - 1];
			}
			tempMap[0] = new boolean[M];
		}
	}

}