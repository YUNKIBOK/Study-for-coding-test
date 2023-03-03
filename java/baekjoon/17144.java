import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */

public class Main {

	static int R, C, T;
	static int machineUp; // 공기청정기 윗 부분
	static int machineDown; // 공기청정기 아랫 부분
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] dusts; // 현재 미세먼지 분포
	static int[][] extraDusts; // 확산될 미세먼지 양

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		dusts = new int[R + 1][C + 1];
		extraDusts = new int[R + 1][C + 1];

		// 미세먼지 분포 및 공기청정기 좌표 저장
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= C; j++) {
				dusts[i][j] = Integer.parseInt(st.nextToken());
				if (dusts[i][j] == -1 && machineUp == 0) {
					machineUp = i;
				} else if (dusts[i][j] == -1 && machineDown == 0) {
					machineDown = i;
				}
			}
		}

//		System.out.println(machineUp + " " + machineDown);

//		System.out.println("처음");
//		for (int i = 1; i <= R; i++) {
//			for (int j = 1; j <= C; j++) {
//				System.out.print(dusts[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int t = 0; t < T; t++) {
			// 미세먼지 확산시키기
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					int nr, nc;
					int spreadCnt = 0;
					for (int d = 0; d < 4; d++) {
						nr = r + dr[d];
						nc = c + dc[d];
						if (1 <= nr && nr <= R && 1 <= nc && nc <= C && dusts[nr][nc] != -1) {
							extraDusts[nr][nc] += dusts[r][c] / 5;
							spreadCnt++;
						}
					}
					dusts[r][c] -= (dusts[r][c] / 5) * spreadCnt;
				}
			}
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					dusts[r][c] += extraDusts[r][c];
					extraDusts[r][c] = 0;
				}
			}

//			System.out.println("확산 후");
//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					System.out.print(dusts[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 반시계 방향 회전
			int upLeft = dusts[1][1];
			int upRight = dusts[1][C];
			int downRight = dusts[machineUp][C];
			int downLeft = 0;

			for (int p = C; p > 2; p--) {
				dusts[machineUp][p] = dusts[machineUp][p - 1];
			}
			dusts[machineUp][2] = downLeft;

			for (int p = 1; p < machineUp; p++) {
				dusts[p][C] = dusts[p + 1][C];
			}

			dusts[machineUp - 1][C] = downRight;

			for (int p = 1; p < C; p++) {
				dusts[1][p] = dusts[1][p + 1];
			}
			dusts[1][C - 1] = upRight;

			for (int p = machineUp - 1; p > 1; p--) {
				dusts[p][1] = dusts[p - 1][1];
			}
			dusts[2][1] = upLeft;

			// 시계 방향 회전

			upLeft = 0;
			upRight = dusts[machineDown][C];
			downRight = dusts[R][C];
			downLeft = dusts[R][1];

			for (int p = C; p > 1; p--) {
				dusts[machineDown][p] = dusts[machineDown][p - 1];
			}
			dusts[machineDown][2] = upLeft;

			for (int p = R; p > machineDown; p--) {
				dusts[p][C] = dusts[p - 1][C];
			}
			dusts[machineDown + 1][C] = upRight;

			for (int p = 1; p < C; p++) {
				dusts[R][p] = dusts[R][p + 1];
			}
			dusts[R][C - 1] = downRight;

			for (int p = machineDown + 1; p < R; p++) {
				dusts[p][1] = dusts[p + 1][1];
			}
			dusts[R - 1][1] = downLeft;
		}

//		System.out.println("회전 후");
//		for (int i = 1; i <= R; i++) {
//			for (int j = 1; j <= C; j++) {
//				System.out.print(dusts[i][j] + " ");
//			}
//			System.out.println();
//		}

		int count = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (dusts[i][j] != 0) {
					count += dusts[i][j];
				}
			}
		}
		count += 2; // 공기청정기 제외
		System.out.println(count);
	}

}