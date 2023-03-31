import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 81개의 수가 가장 작은 경우를 찾아야 하므로 0인 칸을 1부터 9까지 넣어보는 시뮬레이션을 한다
 * 완성 되는 하나의 경우만 찾으면 되는데 한 칸에 들어갈 수 있는 수를 찾는 것은 (9+9+9) = 27번의 검사를 해야 한다
 */

public class Main {

	static int[][] numbers = new int[9][9];
	static int zeroCnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int r = 0; r < 9; r++) {
			char[] chars = in.readLine().toCharArray();
			for (int c = 0; c < 9; c++) {
				numbers[r][c] = Integer.parseInt(chars[c] + "");
				if (numbers[r][c] == 0) {
					zeroCnt++;
				}
			}
		}
//		System.out.println(zeroCnt);

		// 시뮬레이션
		simulation(0, 0);
	}

	public static void simulation(int r, int c) {
//		System.out.println(r + " " + c + " " + zeroCnt);
		if (zeroCnt == 0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(numbers[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}

		if (numbers[r][c] == 0) {
			for (int n = 1; n <= 9; n++) {
				int current = n;
				boolean isPossible = true;
				for (int m = 0; m < 9; m++) {
					if (numbers[m][c] == current) {
						isPossible = false;
						break;
					}
					if (numbers[r][m] == current) {
						isPossible = false;
						break;
					}
				}

				if (isPossible == false) {
					continue;
				}

				int tempR = (r / 3) * 3;
				int tempC = (c / 3) * 3;
				test: for (int m = tempR; m < tempR + 3; m++) {
					for (int k = tempC; k < tempC + 3; k++) {
						if (numbers[m][k] == current) {
							isPossible = false;
							break test;
						}
					}
				}

				if (isPossible == false) {
					continue;
				} else {
					numbers[r][c] = current;
					zeroCnt--;
					if (c == 9 - 1) {
						simulation(r + 1, 0);
					} else {
						simulation(r, c + 1);
					}
					zeroCnt++;
					numbers[r][c] = 0;
				}
			}

			return;
		}

		if (c == 9 - 1) {
			simulation(r + 1, 0);
		} else {
			simulation(r, c + 1);
		}

	}

}
