import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 맵을 순회하며 BFS를 사용한다
 * 최악의 경우 300 * 300 * 20 = 1,800,000의 연산을 하므로 2초 안에 연산을 마칠 수 있다
 */

public class Solution {

	static int N;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			char[][] map = new char[N][];
			visited = new boolean[N][N];
			for (int n = 0; n < N; n++) {
				map[n] = in.readLine().toCharArray();
			}
			int count = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.') {
						int currentCount = 0;
						for (int d = 0; d < 8; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (0 <= nr && nr < N && 0 <= nc && nc < N) {
								if (map[nr][nc] == '*') {
									currentCount += 1;
									continue;
								}
							}
						}
						if (currentCount == 0) {
							count += 1;
							BFS(map, r, c);
						}
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.') {
						count += 1;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void BFS(char[][] map, int startRow, int startCol) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(startRow, startCol));
		visited[startRow][startCol] = true;

		while (queue.size() > 0) {
			Point current = queue.poll();
			int currentRow = current.row;
			int currentCol = current.col;
			int currentCount = 0;
			for (int d = 0; d < 8; d++) {
				int nr = currentRow + dr[d];
				int nc = currentCol + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					if (map[nr][nc] == '*') {
						currentCount += 1;
					}
				}
			}
			map[currentRow][currentCol] = (char) (currentCount + '0');
			if (map[currentRow][currentCol] == '0') {
				for (int d = 0; d < 8; d++) {
					int nr = currentRow + dr[d];
					int nc = currentCol + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N) {
						if (map[nr][nc] == '.' && visited[nr][nc] == false) {
							queue.add(new Point(nr, nc));
							visited[nr][nc] = true;
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