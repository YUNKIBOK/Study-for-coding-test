import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (0, 0)에서 DFS를 하면 공기들을 알아낼 수 있다
 * 치즈는 상하좌우 한 칸씩 탐색하여 공기가 있으면 녹인다
 * 사방탐색하며 시뮬레이션한다
 */

public class Main {

	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] isVisited;
	static int R, C;
	static int time;
	static int cheeseCnt;
	static int lastCnt; // 치즈가 모두 녹기 전 존재했던 치즈 개수

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		time = 0;
		lastCnt = 0;

		// 치즈 판 저장
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheeseCnt++;
				}
			}
		}

		while (cheeseCnt > 0) {
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			lastCnt = 0;

			// 공기와 구멍 구분하기
			isVisited = new boolean[R][C];
			map[0][0] = 2;
			isVisited[0][0] = true;
			DFS(0, 0);

			// 녹일 치즈 표시하기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 1) {
						lastCnt++;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d], nc = c + dc[d];
							if (map[nr][nc] == 2) { // 공기를 만나면
								cheeseCnt--;
								map[r][c] = 3;
								break;
							}
						}
					}
				}
			}

			time++;
		}

		System.out.println(time);
		System.out.println(lastCnt);
	}

	public static void DFS(int r, int c) {
		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != 1) {
				if (!isVisited[nr][nc]) {
					isVisited[nr][nc] = true;
					map[nr][nc] = 2;
					DFS(nr, nc);
				}
			}
		}

	}

}
