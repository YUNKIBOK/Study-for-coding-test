import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 사용해서 탈주범이 위치할 수 있는 장소의 개수를 구한다
 * 위치할 수 있는 장소를 탐색할 때마다 카운트한다 
 */

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int count = 0;
	static int N;
	static int M;
	static int L;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dr = { {}, { -1, 0, 1, 0 }, { -1, 0, 1, 0 }, { 0, 0, 0, 0 }, { -1, 0, 0, 0 }, { 0, 0, 1, 0 },
			{ 0, 0, 1, 0 }, { -1, 0, 0, 0 } };
	static int[][] dc = { {}, { 0, 1, 0, -1 }, { 0, 0, 0, 0 }, { 0, 1, 0, -1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 },
			{ 0, 0, 0, -1 }, { 0, 0, 0, -1 } };
	static int[][] enables = { { 1, 2, 5, 6 }, { 1, 3, 6, 7 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 } };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			BFS(R, C);
			sb.append("#").append(t).append(" ").append(count).append("\n");
			count = 0;
		}

		System.out.println(sb.toString());
	}

	private static void BFS(int startRow, int startCol) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(startRow, startCol));
		visited[startRow][startCol] = true;
		count += 1;
		L--;
		while (L > 0) {
			L--;
			int tempCount = queue.size();
			for (int q = 0; q < tempCount; q++) {
				Point current = queue.poll();
				int currentRow = current.row;
				int currentCol = current.col;
				int value = map[currentRow][currentCol];
				for (int d = 0; d < 4; d++) {
					int nr = currentRow + dr[value][d];
					int nc = currentCol + dc[value][d];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == false && map[nr][nc] != 0) {
						for (int p = 0; p < enables[d].length; p++) {
							if (enables[d][p] == map[nr][nc]) {
								visited[nr][nc] = true;
								count += 1;
								queue.add(new Point(nr, nc));
								break;
							}
						}
					}
				}
			}
		}

	}
}

class Point {
	int row;
	int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}