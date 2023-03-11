
import java.util.*;
import java.io.*;

/*
 * 단계에 따라 로봇 청소기가 멈출 때까지 시뮬레이션한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine(), " ");

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		int[][] room = new int[N][M];

		// 방 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				room[i][j] = value;
			}
		}

		int count = 0;

		while (true) {
			// 1. 현재 칸이 청소되지 않은 경우, 현재 칸을 청소한다
			if (room[r][c] == 0) {
				count++;
				room[r][c] = 2;
			}

			int dirtyCount = 0;
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && room[nr][nc] == 0) {
					dirtyCount++;
				}
			}

			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (dirtyCount == 0) {
				nr = r - dr[d];
				nc = c - dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && room[nr][nc] != 1) {
					r = nr;
					c = nc;
					continue;
				} else {
					break;
				}
			} else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
				d--;
				if (d == -1) {
					d = 3;
				}
				nr = r + dr[d];
				nc = c + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && room[nr][nc] == 0) {
					r = nr;
					c = nc;
				}
			}
		}

		System.out.println(count);
	}

}