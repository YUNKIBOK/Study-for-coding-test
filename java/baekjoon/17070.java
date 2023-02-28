import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 파이프 상태에 따라 다르게 시뮬레이션한다
 */

public class Main {

	static int N;
	static int count;
	static int[][] map;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		map = new int[N + 1][N + 1];
		count = 0;

		// 맵 저장
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 시뮬레이션
		simulate(1, 2, 0);
		System.out.println(count);
	}

	// 상태(0: 가로로 놓인 파이프, 1: 대각선으로 놓인 파이프, 2: 세로로 놓인 파이프)
	static void simulate(int currentR, int currentC, int status) {
		// (N, N) 도착 시 카운트하고 종료
		if (currentR == N && currentC == N) {
			count += 1;
			return;
		}

		if (status == 0) { // 가로로 놓인 파이프 옮기기
			int nr, nc;
			// 가로로 옮기기
			nr = currentR;
			nc = currentC + 1;
			if (nr <= N && nc <= N && map[nr][nc] == 0) {
				simulate(nr, nc, 0);
			}

			// 대각선으로 옮기기
			nr = currentR + 1;
			nc = currentC + 1;
			if (nr <= N && nc <= N && map[nr][nc] == 0 && map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
				simulate(nr, nc, 1);
			}
		} else if (status == 1) { // 대각선으로 놓인 파이프 옮기기
			int nr, nc;
			// 가로로 옮기기
			nr = currentR;
			nc = currentC + 1;
			if (nr <= N && nc <= N && map[nr][nc] == 0) {
				simulate(nr, nc, 0);
			}

			// 대각선으로 옮기기
			nr = currentR + 1;
			nc = currentC + 1;
			if (nr <= N && nc <= N && map[nr][nc] == 0 && map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
				simulate(nr, nc, 1);
			}

			// 세로로 옮기기
			nr = currentR + 1;
			nc = currentC;
			if (nr <= N && nc <= N && map[nr][nc] == 0) {
				simulate(nr, nc, 2);
			}
		} else if (status == 2) { // 세로로 놓인 파이프 옮기기
			int nr, nc;

			// 대각선으로 옮기기
			nr = currentR + 1;
			nc = currentC + 1;
			if (nr <= N && nc <= N && map[nr][nc] == 0 && map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
				simulate(nr, nc, 1);
			}

			// 세로로 옮기기
			nr = currentR + 1;
			nc = currentC;
			if (nr <= N && nc <= N && map[nr][nc] == 0) {
				simulate(nr, nc, 2);
			}
		}
	}

}