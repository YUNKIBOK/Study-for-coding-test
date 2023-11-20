import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static Value[][] dp;
	static int N;
	static int[] scores;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		scores = new int[N + 1];
		scores[0] = 0;

		for (int i = 1; i <= N; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}

		if (N == 1) {
			System.out.println(scores[1]);
			System.exit(0);
		}
		dp = new Value[N + 1][2];
		dp[0][0] = new Value(0, 0);
		dp[0][1] = new Value(0, 0);
		dp[1][0] = new Value(scores[1], 1);
		dp[1][1] = new Value(0, 1);

		for (int i = 2; i <= N; i++) {
			if (dp[i - 1][0].cnt == 2) {
				dp[i][0] = new Value(dp[i - 1][1].v + scores[i], 2);
			} else {
				dp[i][0] = new Value(Math.max(dp[i - 1][1].v, dp[i - 1][0].v) + scores[i], 2);
			}

			dp[i][1] = new Value(Math.max(dp[i - 2][1].v, dp[i - 2][0].v) + scores[i], 1);
		}

		System.out.println(Math.max(dp[N][1].v, dp[N][0].v));
	}
}

class Value {
	int v;
	int cnt;

	public Value(int v, int cnt) {
		this.v = v;
		this.cnt = cnt;
	}
}