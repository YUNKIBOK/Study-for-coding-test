import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
 * 오른쪽, 아래쪽으로 가는 경로를 모두 탐색하여 도착지에 도달할 수 있는지 체크한다.
 * 답은 2^63-1보다 작은데 이는 64비트(8바이트) 가 필요하다. 따라서 long을 사용한다.
 * --------------------------------------------------------------------
 * 위 방법은 2^10000의 시간 복잡도를 가진다. 제한 시간 초과가 우려되어 다른 방법을 생각해본다.
 * 경로는 위 또는 왼쪽에서 오는 것의 합이므로 DP를 활용한다.
 * long은 64비트에서 한 비트가 부호비트이므로 2^63-1까지 표현할 수 있다.
 * 근데 몇개 전에서 오는지 지금 값만 보면 알 수 없으니 거꾸로 생각한다.
 * 도착점에서 시작점으로 오는 경로 개수를 구한다.
 */
public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static BigInteger count;
	static int[][] map;
	static long[][] pathCnt;
	static int N;
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		pathCnt = new long[N][N];
		pathCnt[N - 1][N - 1] = 1;
		for (int r = N - 2; r >= 0; r--) {
			if (r + map[r][N - 1] < N) {
				pathCnt[r][N - 1] = (pathCnt[r + map[r][N - 1]][N - 1]);

			}
		}

		for (int c = N - 2; c >= 0; c--) {
			if (c + map[N - 1][c] < N) {
				pathCnt[N - 1][c] = (pathCnt[N - 1][c + map[N - 1][c]]);

			}
		}

		for (int r = N - 2; r >= 0; r--) {
			for (int c = N - 2; c >= 0; c--) {
				if (map[r][c] == 0) {
					pathCnt[r][c] = 0;
				} else {
					if (r + map[r][c] < N) {

						pathCnt[r][c] += (pathCnt[r + map[r][c]][c]);

					}

					if (c + map[r][c] < N) {

						pathCnt[r][c] += (pathCnt[r][c + map[r][c]]);

					}
				}
			}
		}

//		for (int r = 0; r < N; r++) {
//			System.out.println(Arrays.toString(pathCnt[r]));
//		}

		System.out.println(pathCnt[0][0]);

	}

}