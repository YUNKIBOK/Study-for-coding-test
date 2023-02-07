import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 통해 한 칸씩 이동하며 이동 횟수를 센다
 * 맵은 배열로 생성한다
 */

public class Main {

	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String row = in.readLine();
			for (int c = 1; c <= M; c++) {
				map[r][c] = row.charAt(c - 1) - '0';
			}
//			System.out.println(Arrays.toString(map[r]));
		}

		BFS(new boolean[N + 1][M + 1], map, 1, 1);
		System.out.println(map[N][M]);
	}

	public static void BFS(boolean[][] visited, int[][] map, int startRow, int startCol) {
		Queue<Position> queue = new LinkedList<Position>();

		queue.add(new Position(startRow, startCol));
		visited[startRow][startCol] = true;

		while (queue.size() != 0) {
			Position p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int row = p.getRow() + dr[i];
				int col = p.getCol() + dc[i];

				if (row >= 1 && row < map.length && col >= 1 && col < map[0].length && map[row][col] != 0
						&& visited[row][col] == false) {
					queue.add(new Position(row, col));
					visited[row][col] = true;
					map[row][col] = map[p.getRow()][p.getCol()] + 1;
				}
			}
		}
	}

}

class Position {
	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
