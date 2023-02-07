import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 위쪽, 가운데 아래쪽 구간을 나누어 합을 구한다
 */

public class Solution {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
//		System.out.println(T);

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
//			System.out.println(N);
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] numbers = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = numbers[j] - '0';
//					System.out.println(map[i][j]);
				}
			}

//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			// 윗 부분
			int sum = 0;
			for (int row = 0; row < N / 2; row++) {
				for (int col = N / 2 - row; col < N / 2 + row + 1; col++) {
					sum += map[row][col];
				}
			}

			// 중간 부분
			for (int col = 0; col < N; col++) {
				sum += map[N / 2][col];
			}

			// 아랫 부분
			for (int row = N / 2 + 1; row < N; row++) {
				for (int col = row - N / 2; col < 3 * N / 2 - row; col++) {
					sum += map[row][col];
				}
			}

			System.out.println("#" + t + " " + sum);
		}
	}

}
