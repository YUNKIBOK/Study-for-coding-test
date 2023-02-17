import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS로 탐색하고 다음 탐색을 위해 부술 수 있는 벽을 한 칸씩 부순다
 * 목적지에 도달할 수 있을 때까지 위 작업을 반복한다
 */

public class Main {

	static int N;
	static int M;
	static boolean[][] map; // 방은 true, 벽은 false
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			for (int j = 0; j < M; j++) {
				if (row.charAt(j) == '0')
					map[i][j] = true;
				else
					map[i][j] = false;
			}
		}

		int count = -1;
		do {
			visited = new boolean[N][M];
			BFS();
			count++;
		} while (visited[N - 1][M - 1] != true);

		System.out.println(count);
	}

	static void BFS() {
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(new Position(0, 0));
		visited[0][0] = true;

		// 탐색
		while (queue.size() > 0) {
			Position current = queue.poll();
			int currentRow = current.r;
			int currentCol = current.c;

			for (int i = 0; i < 4; i++) {
				int nr = currentRow + dr[i];
				int nc = currentCol + dc[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == false) {
					if (map[nr][nc] == true) {
						queue.add(new Position(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}

		// 다음 탐색을 위해 벽 부수기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == false) {
					boolean isRemovable = false;
					for (int p = 0; p < 4; p++) {
						int nr = i + dr[p];
						int nc = j + dc[p];
						if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == true) {
							isRemovable = true;
							break;
						}
					}

					if (isRemovable) {
						map[i][j] = true;
					}
				}
			}
		}
	}

}

class Position {

	int r;
	int c;

	public Position(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

}
