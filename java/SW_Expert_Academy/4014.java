import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2차원 배열에 경사로 설치 여부를 저장해가며 모든 행과 모든 열을 검사한다
 */

public class Solution {

	static int N, X;
	static int count;
	static int[][] map;
	static boolean[][] isSlope;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			count = 0;

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 행 검사
			rowTest();

			// 열 검사
			colTest();

			sb.append(count).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void rowTest() {
		isSlope = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			int prev = map[r][0];
			int current;
			boolean isPossible = true;
			test: for (int c = 1; c < N; c++) {
				current = map[r][c];
				if (prev == current) {
					continue;
				} else if (Math.abs(prev - current) >= 2) {
					isPossible = false;
					break;
				} else {
					if (prev > current) {
						for (int i = 0; i < X; i++) {
							if (c + i < N && isSlope[r][c + i] == false) {
								isSlope[r][c + i] = true;
							} else {
								isPossible = false;
								break test;
							}
						}
					} else {
						for (int i = 1; i <= X; i++) {
							if (c - i >= 0 && isSlope[r][c - i] == false) {
								isSlope[r][c - i] = true;
							} else {
								isPossible = false;
								break test;
							}
						}
					}
				}
				prev = current;
			}

			if (isPossible) {
				count += 1;
			}
		}
	}

	static void colTest() {
		isSlope = new boolean[N][N];

		for (int c = 0; c < N; c++) {
			int prev = map[0][c];
			int current;
			boolean isPossible = true;
			test: for (int r = 1; r < N; r++) {
				current = map[r][c];
				if (prev == current) {
					continue;
				} else if (Math.abs(prev - current) >= 2) {
					isPossible = false;
					break;
				} else {
					if (prev > current) {
						for (int i = 0; i < X; i++) {
							if (r + i < N && isSlope[r + i][c] == false) {
								isSlope[r + i][c] = true;
							} else {
								isPossible = false;
								break test;
							}
						}
					} else {
						for (int i = 1; i <= X; i++) {
							if (r - i >= 0 && isSlope[r - i][c] == false) {
								isSlope[r - i][c] = true;
							} else {
								isPossible = false;
								break test;
							}
						}
					}
				}
				prev = current;
			}

			if (isPossible) {
				count += 1;
			}
		}
	}

}
