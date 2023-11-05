import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, T;
	static int[][] map;
	static boolean[][] isVisited;
	static int[][] time;
	static int swordPosition;
	static Queue<Integer> queue;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new boolean[N][M];
		time = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					swordPosition = r * M + c;
				}
			}
		}

		queue = new ArrayDeque<>();
		isVisited[0][0] = true;
		queue.add(0);
		time[0][0] = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			int cr = current / M;
			int cc = current % M;
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] != 1 && isVisited[nr][nc] == false) {
					isVisited[nr][nc] = true;
					queue.add(nr * M + nc);
					time[nr][nc] = time[cr][cc] + 1;
				}
			}
		}

//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(time[i]));
//		}

		int answer1 = time[N - 1][M - 1] != 0 ? time[N - 1][M - 1] : 987654321;
		int answer2 = time[swordPosition / M][swordPosition % M] != 0 ? time[swordPosition / M][swordPosition % M]
				+ Math.abs((N - 1) - (swordPosition / M)) + Math.abs((M - 1) - (swordPosition % M)) : 987654321;

//		System.out.println(answer1 + " "+answer2);		
		int answer = Math.min(answer1, answer2);
		if (answer == 987654321 || answer > T) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}
}