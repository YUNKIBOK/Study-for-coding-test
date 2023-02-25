import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * M개의 활성 바이러스를 조합으로 선택한다 -> 최악의 경우 10C5 = 252
 * BFS를 활용하여 바이러스를 퍼트리는 시뮬레이션을 한다 -> 최악의 경우 50 * 50 = 2,500
 * 최악의 경우 252 * 2,500 < 1억이므로 시간 안에 해결할 수 있다
 */

public class Main {

	static int N;
	static int M;
	static int minSecond;
	static int zeroCount;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 연구실 맵
	static int[][] map;

	// 바이러스 위치 목록
	static List<Position> viruses;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기회
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minSecond = Integer.MAX_VALUE;
		map = new int[N][N];
		viruses = new ArrayList<>();

		// 연구실 맵과 바이러스 위치 저장
		zeroCount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					viruses.add(new Position(i, j));
				} else if (map[i][j] == 0)
					zeroCount++;
			}
		}

		// 처음부터 빈 칸이 없는 경우 빠른 종료
		if (zeroCount == 0) {
			System.out.println(0);
			System.exit(0);
		}

		// 황성 바이러스 조합 후 시뮬레이션
		combination(0, 0, new int[M]);

		if (minSecond == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minSecond);
		}
	}

	static void combination(int count, int start, int[] selection) {
		if (count >= M) {
			// 임시 맵 생성
			int[][] tempMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, N);
			}

			// BFS로 활성 바이러스 퍼트리기
			boolean[][] visited = new boolean[N][N];
			Queue<Position> queue = new ArrayDeque<>();
			for (int i = 0; i < M; i++) {
				visited[viruses.get(selection[i]).r][viruses.get(selection[i]).c] = true;
				queue.add(new Position(viruses.get(selection[i]).r, viruses.get(selection[i]).c));
			}

//			System.out.println(Arrays.toString(selection));
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(tempMap[i]));
//			}
//			System.out.println();

			int second = 0;
			int tempZeroCount = zeroCount;
			while (queue.size() > 0) {
				// 모든 빈 칸에 바이러스가 퍼졌는지 체크
				if (tempZeroCount == 0) {
					// 모든 빈 칸에 바이러스가 퍼졌다면 최소 시간 갱신
					minSecond = Math.min(minSecond, second);
					break;
				}
				second += 1;
				int size = queue.size();
				while (size > 0) {
					size--;
					Position current = queue.poll();
					for (int i = 0; i < 4; i++) {
						int nr = current.r + dr[i];
						int nc = current.c + dc[i];
						if (0 <= nr && nr < N && 0 <= nc && nc < N && (tempMap[nr][nc] == 0 || tempMap[nr][nc] == 2)) {
							if (visited[nr][nc] == false) {
								if (map[nr][nc] == 0)
									tempZeroCount--;
								visited[nr][nc] = true;
								tempMap[nr][nc] = second;
								queue.add(new Position(nr, nc));
							}
						}
					}
				}
			}

//			System.out.println(Arrays.toString(selection));
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(tempMap[i]));
//			}
//			System.out.println();

			return;
		}

		for (int i = start; i < viruses.size(); i++) {
			selection[count] = i;
			combination(count + 1, i + 1, selection);
		}
	}

}

class Position {

	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Position [r=" + r + ", c=" + c + "]";
	}

}