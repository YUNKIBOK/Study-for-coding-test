import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 통해 매초마다 수연이가 여신님께 가도록한다
 * 다음으로 매초마다 악마들을 퍼트린다
 * 만약 이러한 과정을 반복하면서 수연이가 더 이상 전진할 수 없으면 GAME OVER이다
 * 반면에 수연이가 여신님께 안전하게 도착하면 시간을 기록한다
 */

public class Solution {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N;
	static int M;
	static Position god;
	static Position suyeon;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Position> queue;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 지도, 여신의 위치, 수연이의 위치 저장
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String row = in.readLine();
				for (int j = 0; j < M; j++) {
					char value = row.charAt(j);
					map[i][j] = value;
					if (value == 'D') {
						god = new Position(i, j);
					} else if (value == 'S') {
						suyeon = new Position(i, j);
					}
				}
			}

			// 수연이를 이동시킬 준비
			queue = new ArrayDeque<>();
			visited = new boolean[N][M];
			queue.add(suyeon);
			visited[suyeon.r][suyeon.c] = true;

			// 매초마다 수연이를 이동시키고 악마를 퍼트린다
			int second = 0;
			while (true) {
				second++;
				// 악마 퍼트리기
				spreadDevil();
//				for(int i=0; i<N; i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
//				System.out.println();

				// 수연이 움직이기
				moveSuyeon();
				// 수연이가 여신님께 도착한 경우
				if (visited[god.r][god.c] == true) {
					sb.append(String.format("#%d %d\n", t, second));
					break;
				}
				// 수연이가 더 이상 전진할 수 없는 경우
				if (queue.size() == 0) {
					sb.append(String.format("#%d %s\n", t, "GAME OVER"));
					break;
				}
			}
		}

		System.out.println(sb.toString());
	}

	static void moveSuyeon() {
		int count = queue.size();
		while (count > 0) {
			count--;
			Position current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = current.r + dr[i];
				int nc = current.c + dc[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == false) {
					if (map[nr][nc] == '.' || map[nr][nc] == 'D') {
						queue.add(new Position(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	static void spreadDevil() {
		char[][] tempMap = new char[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, M);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempMap[i][j] == '*') {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (0 <= nr && nr < N && 0 <= nc && nc < M && (map[nr][nc] == '.' || map[nr][nc] == 'S')) {
							map[nr][nc] = '*';
						}
					}
				}
			}
		}
	}

}

class Position {

	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

}