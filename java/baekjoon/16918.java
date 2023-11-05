import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int R, C, N;
	static int[][] map;
	static Queue<Integer> queue;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		// 1초 후
		for (int r = 0; r < R; r++) {
			char[] row = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (row[c] == '.') {
					map[r][c] = 0;
				} else if (row[c] == 'O') {
					map[r][c] = 3;
				}
			}
		}

//		for (int r = 0; r < R; r++) {
//			System.out.println(Arrays.toString(map[r]));
//		}
//
//		System.out.println();

		// n초 후
		for (int n = 2; n <= N; n++) {

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) {
						map[r][c]--;
					}
				}
			}

//			for (int r = 0; r < R; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//
//			System.out.println();

			if (n % 2 == 0) {
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c] == 0) {
							map[r][c] = 4;
						}
					}
				}
			} else {
				boolean[][] isVisited = new boolean[R][C];
				queue = new ArrayDeque<>();

				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c] == 1) {
							// bfs
							queue.add(r * C + c);
							map[r][c] = 0;
							isVisited[r][c] = true;

							while (!queue.isEmpty()) {
								int current = queue.poll();
								int cr = current / C;
								int cc = current % C;

								map[cr][cc] = 0;
								for (int d = 0; d < 4; d++) {
									int nr = cr + dr[d];
									int nc = cc + dc[d];
									if (0 <= nr && nr < R && 0 <= nc && nc < C && isVisited[nr][nc] == false
											&& map[nr][nc] != 0) {
										if (map[nr][nc] == 1) {
											queue.add(nr * C + nc);
										} else {
											map[nr][nc] = 0;
										}
										isVisited[nr][nc] = true;
									}
								}
							}
						}
					}
				}

			}

//			for (int r = 0; r < R; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//
//			System.out.println();

		}

		for (

				int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					sb.append(".");
				} else {
					sb.append("O");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}
}