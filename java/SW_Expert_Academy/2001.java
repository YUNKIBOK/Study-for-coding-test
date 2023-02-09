import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 파리채를 내리칠 수 있는 영역의 합 중 가장 큰 값을 구한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = Integer.MIN_VALUE;
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - M + 1; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					int sum = 0;
					for (int p = 0; p < M; p++) {
						for (int q = 0; q < M; q++) {
							sum += map[i + p][j + q];
						}
					}
					max = Math.max(max, sum);
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		out.append(sb.toString());
		out.flush();
		out.close();
		in.close();
		sb.setLength(0);
	}

}