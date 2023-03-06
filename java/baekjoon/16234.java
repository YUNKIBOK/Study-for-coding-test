import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, L, R;
	static int[][] populations;
	static int[][] unions;
	static boolean[][] visited;
	static int unionCount;
	static int tempSum;
	static int tempCount;
	static Map<Integer, Integer> sums;
	static Map<Integer, Integer> counts;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		populations = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				populations[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		unionCount = 0;
		int time = 0;
		while (unionCount != N * N) {

			unionCount = 0;
			unions = new int[N][N];
			visited = new boolean[N][N];
			sums = new HashMap<>();
			counts = new HashMap<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c] == false) {
						// DFS
						tempSum = 0;
						tempCount = 0;
						DFS(r, c);
						sums.put(unionCount, tempSum);
						counts.put(unionCount, tempCount);
						unionCount += 1;
					}
				}
			}
			if (unionCount == N * N) {
				break;
			}
//			System.out.println(sums);
//			System.out.println(counts);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int value = unions[i][j];
					populations[i][j] = sums.get(value) / counts.get(value);
				}
			}
			time++;

		}
		System.out.println(time);
	}

	static void DFS(int r, int c) {
		tempCount += 1;
		tempSum += populations[r][c];
		visited[r][c] = true;
		unions[r][c] = unionCount;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < N) {
				int diff = Math.abs(populations[r][c] - populations[nr][nc]);
				if (L <= diff && diff <= R && visited[nr][nc] == false) {
					DFS(nr, nc);
				}
			}
		}
	}
}