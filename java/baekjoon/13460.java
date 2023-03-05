import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/*
 * 중복 순열로 보드를 기울이는 시뮬레이션을 한다
 */
public class Main {

	static int N, M;
	static char[][] map;
	static char[][] tempMap;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int min;
	static int redR, redC;
	static int blueR, bludC;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			char[] temp = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j];
				if (map[i][j] == 'R') {
					redR = i;
					redC = j;
				} else if (map[i][j] == 'B') {
					blueR = i;
					bludC = j;
				}
			}
		}

		duplicatePermutation(0, new int[10]);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	static void duplicatePermutation(int count, int[] commands) {
		if (count >= 10) {
			tempMap = new char[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}

			int tempRedR = redR;
			int tempRedC = redC;

			int tempBlueR = blueR;
			int tempBlueC = bludC;

			boolean isBlueEscape = false;
			boolean isRedEscape = false;

			int nr, nc;
			for (int i = 1; i <= 10; i++) {
				switch (commands[i - 1]) {
				case 0:// 상
					if (tempRedR < tempBlueR) {
						tempMap[tempRedR][tempRedC] = '.';
						nr = tempRedR + dr[0];
						while (tempMap[nr][tempRedC] == '.' || tempMap[nr][tempRedC] == 'O') {
							if (tempMap[nr][tempRedC] == 'O') {
								isRedEscape = true;
							}
							tempRedR = nr;
							nr += dr[0];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';

						tempMap[tempBlueR][tempBlueC] = '.';
						nr = tempBlueR + dr[0];
						while (tempMap[nr][tempBlueC] == '.' || tempMap[nr][tempBlueC] == 'O') {
							if (tempMap[nr][tempBlueC] == 'O') {
								isBlueEscape = true;
								break;
							}
							tempBlueR = nr;
							nr += dr[0];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';
					} else {
						tempMap[tempBlueR][tempBlueC] = '.';
						nr = tempBlueR + dr[0];
						while (tempMap[nr][tempBlueC] == '.' || tempMap[nr][tempBlueC] == 'O') {
							if (tempMap[nr][tempBlueC] == 'O') {
								isBlueEscape = true;
							}
							tempBlueR = nr;
							nr += dr[0];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';

						tempMap[tempRedR][tempRedC] = '.';
						nr = tempRedR + dr[0];
						while (tempMap[nr][tempRedC] == '.' || tempMap[nr][tempRedC] == 'O') {
							if (tempMap[nr][tempRedC] == 'O') {
								isRedEscape = true;
								break;
							}
							tempRedR = nr;
							nr += dr[0];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';
					}
					break;
				case 1:// 하
					if (tempRedR > tempBlueR) {
						tempMap[tempRedR][tempRedC] = '.';
						nr = tempRedR + dr[1];
						while (tempMap[nr][tempRedC] == '.' || tempMap[nr][tempRedC] == 'O') {
							if (tempMap[nr][tempRedC] == 'O') {
								isRedEscape = true;
							}
							tempRedR = nr;
							nr += dr[1];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';

						tempMap[tempBlueR][tempBlueC] = '.';
						nr = tempBlueR + dr[1];
						while (tempMap[nr][tempBlueC] == '.' || tempMap[nr][tempBlueC] == 'O') {
							if (tempMap[nr][tempBlueC] == 'O') {
								isBlueEscape = true;
								break;
							}
							tempBlueR = nr;
							nr += dr[1];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';
					} else {
						tempMap[tempBlueR][tempBlueC] = '.';
						nr = tempBlueR + dr[1];
						while (tempMap[nr][tempBlueC] == '.' || tempMap[nr][tempBlueC] == 'O') {
							if (tempMap[nr][tempBlueC] == 'O') {
								isBlueEscape = true;
							}
							tempBlueR = nr;
							nr += dr[1];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';

						tempMap[tempRedR][tempRedC] = '.';
						nr = tempRedR + dr[1];
						while (tempMap[nr][tempRedC] == '.' || tempMap[nr][tempRedC] == 'O') {
							if (tempMap[nr][tempRedC] == 'O') {
								isRedEscape = true;
								break;
							}
							tempRedR = nr;
							nr += dr[1];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';
					}
					break;
				case 2:// 좌
					if (tempRedC < tempBlueC) {
						tempMap[tempRedR][tempRedC] = '.';
						nc = tempRedC + dc[2];
						while (tempMap[tempRedR][nc] == '.' || tempMap[tempRedR][nc] == 'O') {
							if (tempMap[tempRedR][nc] == 'O') {
								isRedEscape = true;
							}
							tempRedC = nc;
							nc += dc[2];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';

						tempMap[tempBlueR][tempBlueC] = '.';
						nc = tempBlueC + dc[2];
						while (tempMap[tempBlueR][nc] == '.' || tempMap[tempBlueR][nc] == 'O') {
							if (tempMap[tempBlueR][nc] == 'O') {
								isBlueEscape = true;
								break;
							}
							tempBlueC = nc;
							nc += dc[2];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';
					} else {
						tempMap[tempBlueR][tempBlueC] = '.';
						nc = tempBlueC + dc[2];
						while (tempMap[tempBlueR][nc] == '.' || tempMap[tempBlueR][nc] == 'O') {
							if (tempMap[tempBlueR][nc] == 'O') {
								isBlueEscape = true;
							}
							tempBlueC = nc;
							nc += dc[2];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';

						tempMap[tempRedR][tempRedC] = '.';
						nc = tempRedC + dc[2];
						while (tempMap[tempRedR][nc] == '.' || tempMap[tempRedR][nc] == 'O') {
							if (tempMap[tempRedR][nc] == 'O') {
								isRedEscape = true;
								break;
							}
							tempRedC = nc;
							nc += dc[2];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';

					}
					break;
				case 3:// 우
					if (tempRedC > tempBlueC) {
						tempMap[tempRedR][tempRedC] = '.';
						nc = tempRedC + dc[3];
						while (tempMap[tempRedR][nc] == '.' || tempMap[tempRedR][nc] == 'O') {
							if (tempMap[tempRedR][nc] == 'O') {
								isRedEscape = true;
							}
							tempRedC = nc;
							nc += dc[3];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';

						tempMap[tempBlueR][tempBlueC] = '.';
						nc = tempBlueC + dc[3];
						while (tempMap[tempBlueR][nc] == '.' || tempMap[tempBlueR][nc] == 'O') {
							if (tempMap[tempBlueR][nc] == 'O') {
								isBlueEscape = true;
								break;
							}
							tempBlueC = nc;
							nc += dc[3];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';
					} else {
						tempMap[tempBlueR][tempBlueC] = '.';
						nc = tempBlueC + dc[3];
						while (tempMap[tempBlueR][nc] == '.' || tempMap[tempBlueR][nc] == 'O') {
							if (tempMap[tempBlueR][nc] == 'O') {
								isBlueEscape = true;
							}
							tempBlueC = nc;
							nc += dc[3];
						}
						if (isBlueEscape == false)
							tempMap[tempBlueR][tempBlueC] = 'B';

						tempMap[tempRedR][tempRedC] = '.';
						nc = tempRedC + dc[3];
						while (tempMap[tempRedR][nc] == '.' || tempMap[tempRedR][nc] == 'O') {
							if (tempMap[tempRedR][nc] == 'O') {
								isRedEscape = true;
								break;
							}
							tempRedC = nc;
							nc += dc[3];
						}
						if (isRedEscape == false)
							tempMap[tempRedR][tempRedC] = 'R';
					}
					break;
				}

//				for(int p=0; p<N; p++) {
//					System.out.println(Arrays.toString(tempMap[p]));
//				}
//				System.out.println();

				if (isRedEscape == true && isBlueEscape == false) {
//					System.out.println(Arrays.toString(commands));
//					System.out.println(i + " " + min);
					min = Math.min(min, i);
					return;
				} else if (isBlueEscape == true) {
					return;
				}
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			commands[count] = i;
			duplicatePermutation(count + 1, commands);
		}
	}

}