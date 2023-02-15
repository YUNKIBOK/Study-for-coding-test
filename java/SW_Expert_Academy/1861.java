import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 방 번호는 유니크하고 정확히 1 더 큰 방으로 이동할 수 있다 -> DFS를 사용한다
 * N^2 * 27 = 최대 27,000,000이므로 시간은 넉넉하다(DFS에서 특별하게 다른 로직을 수행하지 않으므로)
 */

public class Solution {

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[][] rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			int minRoom = Integer.MAX_VALUE;

			// 모든 방에서 시뮬레이션하여 최대로 갈 수 있는 방의 개수와 처음에 출발해야 하는 방 번호를 구한다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int result = DFS(1, rooms, i, j);
					if (max < result) {
						max = result;
						minRoom = rooms[i][j];
					} else if(max == result) {
						if(minRoom > rooms[i][j]) {
							minRoom = rooms[i][j];
						}
					}
				}
			}

			sb.append(String.format("#%d %d %d\n", t, minRoom, max));
		}

		System.out.println(sb.toString());
	}

	static int DFS(int count, int[][] rooms, int currentRow, int currentCol) {
		for (int i = 0; i < 4; i++) {
			int nr = currentRow + dr[i];
			int nc = currentCol + dc[i];

			if (0 <= nr && nr <= rooms.length - 1 && 0 <= nc && nc <= rooms.length - 1
					&& rooms[nr][nc] == rooms[currentRow][currentCol] + 1) {
				return DFS(count + 1, rooms, nr, nc);
			}
		}
		return count;
	}

}