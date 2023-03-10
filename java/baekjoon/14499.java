
import java.util.*;
import java.io.*;

/*
 * 주사위 굴릴 때마다 면 번호의 변화를 구현한다
 */
public class Main {

	// 주사위 윗 면, 아랫 면, 동쪽 면, 서쪽 면, 북쪽 면, 남쪽 면
	static int top, bottom;
	static int east, west, north, south;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 지도 저장
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 동 서 남 북 명령어
		int[] dr = { 0, 0, 0, -1, 1 };
		int[] dc = { 0, 1, -1, 0, 0 };

		// 주사위 초기화
		int[] dice = { 0, 0, 0, 0, 0, 0, 0 };
		bottom = 6;
		top = 1;
		east = 3;
		west = 4;
		north = 2;
		south = 5;

		// 시뮬레이션
		int nr, nc;
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < K; i++) {
//			System.out.println(Arrays.toString(dice));
//			System.out.println(bottom + " " + top);
			int command = Integer.parseInt(st.nextToken());
			nr = x + dr[command];
			nc = y + dc[command];
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				// 주사위 이동
				if (command == 2) { // 서쪽 1번은 동쪽으로 3번 이동한 것과 같다
					rollDice(1);
					rollDice(1);
					rollDice(1);
				} else if (command == 4) { // 남쪽 1번은 북쪽으로 3번 이동한 것과 같다
					rollDice(3);
					rollDice(3);
					rollDice(3);
				} else {
					rollDice(command);
				}
				if (map[nr][nc] != 0) {
					dice[bottom] = map[nr][nc];
					map[nr][nc] = 0;
				} else {
					map[nr][nc] = dice[bottom];
				}
				sb.append(dice[top]).append("\n");
				x = nr;
				y = nc;
			}
		}

		System.out.println(sb.toString());
	}

	static void rollDice(int command) {
		switch (command) {
		case 1:
			if (bottom == 1) {
				if (north == 2) {
					bottom = 3;
					top = 4;
					east = 6;
					west = 1;
				} else if (north == 3) {
					bottom = 5;
					top = 2;
					east = 6;
					west = 1;
				} else if (north == 5) {
					bottom = 4;
					top = 3;
					east = 6;
					west = 1;
				} else if (north == 4) {
					bottom = 2;
					top = 5;
					east = 6;
					west = 1;
				}
			} else if (bottom == 2) {
				if (north == 6) {
					bottom = 3;
					top = 4;
					east = 5;
					west = 2;
				} else if (north == 3) {
					bottom = 1;
					top = 6;
					east = 5;
					west = 2;
				} else if (north == 1) {
					bottom = 4;
					top = 3;
					east = 5;
					west = 2;
				} else if (north == 4) {
					bottom = 6;
					top = 1;
					east = 5;
					west = 2;
				}
			} else if (bottom == 3) {
				if (north == 2) {
					bottom = 6;
					top = 1;
					east = 4;
					west = 3;
				} else if (north == 6) {
					bottom = 5;
					top = 2;
					east = 4;
					west = 3;
				} else if (north == 5) {
					bottom = 1;
					top = 6;
					east = 4;
					west = 3;
				} else if (north == 1) {
					bottom = 2;
					top = 5;
					east = 4;
					west = 3;
				}
			} else if (bottom == 4) {
				if (north == 2) {
					bottom = 1;
					top = 6;
					east = 3;
					west = 4;
				} else if (north == 1) {
					bottom = 5;
					top = 2;
					east = 3;
					west = 4;
				} else if (north == 5) {
					bottom = 6;
					top = 1;
					east = 3;
					west = 4;
				} else if (north == 6) {
					bottom = 2;
					top = 5;
					east = 3;
					west = 4;
				}
			} else if (bottom == 5) {
				if (north == 1) {
					bottom = 3;
					top = 4;
					east = 2;
					west = 5;
				} else if (north == 3) {
					bottom = 6;
					top = 1;
					east = 2;
					west = 5;
				} else if (north == 6) {
					bottom = 4;
					top = 3;
					east = 2;
					west = 5;
				} else if (north == 4) {
					bottom = 1;
					top = 6;
					east = 2;
					west = 5;
				}
			} else if (bottom == 6) {
				if (north == 5) {
					bottom = 3;
					top = 4;
					east = 1;
					west = 6;
				} else if (north == 3) {
					bottom = 2;
					top = 5;
					east = 1;
					west = 6;
				} else if (north == 2) {
					bottom = 4;
					top = 3;
					east = 1;
					west = 6;
				} else if (north == 4) {
					bottom = 5;
					top = 2;
					east = 1;
					west = 6;
				}
			}
			break;

		case 3:
			if (bottom == 1) {
				if (north == 2) {
					bottom = 2;
					top = 5;
					north = 6;
					south = 1;
				} else if (north == 3) {
					bottom = 3;
					top = 4;
					north = 6;
					south = 1;
				} else if (north == 5) {
					bottom = 5;
					top = 2;
					north = 6;
					south = 1;
				} else if (north == 4) {
					bottom = 4;
					top = 3;
					north = 6;
					south = 1;
				}
			} else if (bottom == 2) {
				if (north == 6) {
					bottom = 6;
					top = 1;
					north = 5;
					south = 2;
				} else if (north == 3) {
					bottom = 3;
					top = 4;
					north = 5;
					south = 2;
				} else if (north == 1) {
					bottom = 1;
					top = 6;
					north = 5;
					south = 2;
				} else if (north == 4) {
					bottom = 4;
					top = 3;
					north = 5;
					south = 2;
				}
			} else if (bottom == 3) {
				if (north == 2) {
					bottom = 2;
					top = 5;
					north = 4;
					south = 3;
				} else if (north == 6) {
					bottom = 6;
					top = 1;
					north = 4;
					south = 3;
				} else if (north == 5) {
					bottom = 5;
					top = 2;
					north = 4;
					south = 3;
				} else if (north == 1) {
					bottom = 1;
					top = 6;
					north = 4;
					south = 3;
				}
			} else if (bottom == 4) {
				if (north == 2) {
					bottom = 2;
					top = 5;
					north = 3;
					south = 4;
				} else if (north == 1) {
					bottom = 1;
					top = 6;
					north = 3;
					south = 4;
				} else if (north == 5) {
					bottom = 5;
					top = 2;
					north = 3;
					south = 4;
				} else if (north == 6) {
					bottom = 6;
					top = 1;
					north = 3;
					south = 4;
				}
			} else if (bottom == 5) {
				if (north == 1) {
					bottom = 1;
					top = 6;
					north = 2;
					south = 5;
				} else if (north == 3) {
					bottom = 3;
					top = 4;
					north = 2;
					south = 5;
				} else if (north == 6) {
					bottom = 6;
					top = 1;
					north = 2;
					south = 5;
				} else if (north == 4) {
					bottom = 4;
					top = 3;
					north = 2;
					south = 5;
				}
			} else if (bottom == 6) {
				if (north == 5) {
					bottom = 5;
					top = 2;
					north = 1;
					south = 6;
				} else if (north == 3) {
					bottom = 3;
					top = 4;
					north = 1;
					south = 6;
				} else if (north == 2) {
					bottom = 2;
					top = 5;
					north = 1;
					south = 6;
				} else if (north == 4) {
					bottom = 4;
					top = 3;
					north = 1;
					south = 6;
				}
			}
			break;
		}
	}

}