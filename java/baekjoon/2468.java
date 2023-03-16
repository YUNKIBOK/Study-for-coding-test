
import java.util.*;
import java.io.*;

/*
 * 내리는 비의 양을 0부터 최대 지형 높이까지 순회하며 테스트한다
 * 비의 양보다 낮은 지점은 모두 false로 만들고 나머지 지점은 true로 만든 임시 지도를 DFS 탐색하여 안전 영역 개수를 구한다
 */
public class Main {

	static int N;
	static int max; // 안전 구역 최대 개수
	static int until; // 내리는 비 상한선
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] tempMap; // DFS용 임시 맵

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		max = Integer.MIN_VALUE;
		until = Integer.MIN_VALUE;
		map = new int[N][N];

		// 맵 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				until = Math.max(until, map[i][j]);
			}
		}

		// 0 ~ until까지 높이만큼 비 내리는 시뮬레이션
		for (int k = 0; k <= until; k++) {
			// 임시 지도 생성
			tempMap = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > k) {
						tempMap[i][j] = true;
					}
				}
			}

			// 안전 구역 세고 갱신
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tempMap[i][j] == true) {
						count++;
						dfs(i, j);
					}
				}
			}
			max = Math.max(max, count);
		}

		System.out.println(max);
	}

	static void dfs(int r, int c) {
		tempMap[r][c] = false;

		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if (0 <= nr && nr < N && 0 <= nc && nc < N && tempMap[nr][nc] == true) {
				dfs(nr, nc);
			}
		}
	}

}
