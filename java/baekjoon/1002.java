import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int T;
	static int x1, y1, r1, x2, y2, r2;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());

			int plus = r1 + r2;
			int minus = Math.abs(r1 - r2);
			double distance = Math
					.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2) * 1.0 + Math.abs(y1 - y2) * Math.abs(y1 - y2) * 1.0);

			if (distance == 0) {
				if (r1 == r2) {
					sb.append(-1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if (distance < minus) {
				sb.append(0).append("\n");
			} else if (distance == minus) {
				sb.append(1).append("\n");
			} else if (distance > minus && distance < plus) {
				sb.append(2).append("\n");
			} else if (distance == plus) {
				sb.append(1).append("\n");
			} else if (distance > plus) {
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}