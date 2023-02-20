import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * N * N 부지를 탐색하여 가장 높은 봉우리 정보를 저장한다
 * 가장 높은 봉우리에서 시뮬레이션을 시작한다
 * 시뮬레이션은 DFS로 수행한다
 * 봉우리는 높을수록 가장 긴 등산로를 만들 수 있는 가능성이 높으므로
 * 봉우리를 깎을 때 최소한 갂는다
 * 4방 탐색 중 이전 봉우리를 다시 방문해서는 안된다
 */

public class Solution {

	static int N;
	static int K;
	static int longest = 1;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 지도 저장
			map = new int[N][N];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			// 가장 높은 봉우리 저장
			List<Position> highest = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max)
						highest.add(new Position(i, j));
				}
			}

			// 가장 높은 봉우리를 순회하며 등산로 만들기 시뮬레이션
			visited = new boolean[N][N];
			for (Position p : highest) {
				visited[p.r][p.c] = true;
				DFS(1, p.r, p.c, true);
				visited[p.r][p.c] = false;
			}
			sb.append(String.format("#%d %d\n", t, longest));

			// 다음 테스트 케이스를 위한 초기화
			longest = Integer.MIN_VALUE;
		}

		System.out.println(sb.toString());
	}

	static void DFS(int length, int startR, int startC, boolean canFix) {
		// 현재 위치에서 등산로 만들기를 이어나갈 수 있는지 체크
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nr = startR + dr[i];
			int nc = startC + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == false) {
				// 현재 위치보다 낮은 곳이 있는지 확인
				if (map[startR][startC] > map[nr][nc]) {
					count++;
				}
				// 깎았을 때 현재 위치보다 낮은 곳이 있는지 확인
				if (canFix && map[startR][startC] <= map[nr][nc] && map[nr][nc] - K < map[startR][startC]) {
					count++;
				}
			}
		}

		// 등산로 만들기를 이어나갈 수 없으면 갱신
		if (count == 0) {
			longest = Math.max(longest, length);
			return;
		}

		// 등산로 이어서 만들기
		for (int i = 0; i < 4; i++) {
			int nr = startR + dr[i];
			int nc = startC + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == false) {
				if (map[startR][startC] > map[nr][nc]) {
					visited[nr][nc] = true;
					DFS(length + 1, nr, nc, canFix);
					visited[nr][nc] = false;
				}
				if (canFix && map[startR][startC] <= map[nr][nc] && map[nr][nc] - K < map[startR][startC]) {
					int temp = map[nr][nc];
					map[nr][nc] = map[startR][startC] - 1;
					visited[nr][nc] = true;
					DFS(length + 1, nr, nc, false);
					visited[nr][nc] = false;
					map[nr][nc] = temp;
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