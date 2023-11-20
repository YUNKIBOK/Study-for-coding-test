import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static char[][] map;
	static boolean[][] isFire;
	static boolean[][] isVisited;
	static int time;
	static Queue<Integer> jihun;
	static Queue<Integer> fire;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		fire = new ArrayDeque<>();
		jihun = new ArrayDeque<>();
		isFire = new boolean[R][C];
		isVisited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = row.charAt(c);
				if (map[r][c] == 'J') {
					jihun.add(r * C + c);
					isVisited[r][c] = true;
				} else if (map[r][c] == 'F') {
					fire.add(r * C + c);
					isFire[r][c] = true;
				}
			}
		}

		time = 0;

		while (!jihun.isEmpty()) {
			time++;

			int size = fire.size();
			for (int s = 0; s < size; s++) {
				int current = fire.poll();
				int cr = current / C;
				int cc = current % C;
				for (int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != '#' && isFire[nr][nc] == false) {
						isFire[nr][nc] = true;
						map[nr][nc] = 'F';
						fire.add(nr * C + nc);
					}
				}
			}

			size = jihun.size();
			for (int s = 0; s < size; s++) {
				int current = jihun.poll();
				int cr = current / C;
				int cc = current % C;
				if (cr == 0 || cr == R - 1 || cc == 0 || cc == C - 1) {
					System.out.println(time);
					System.exit(0);
				}
				for (int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] == '.' && isVisited[nr][nc] == false) {
						isVisited[nr][nc] = true;
						jihun.add(nr * C + nc);
					}

				}
			}
		}

		System.out.println("IMPOSSIBLE");

	}
}