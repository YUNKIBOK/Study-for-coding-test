import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[][] count;
	static int T;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		count = new int[40 + 1][2];
		count[0][0] = 1;
		count[1][1] = 1;
		for (int n = 2; n <= 40; n++) {
			count[n][0] = count[n - 1][0] + count[n - 2][0];
			count[n][1] = count[n - 1][1] + count[n - 2][1];
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append(count[n][0]).append(" ").append(count[n][1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}