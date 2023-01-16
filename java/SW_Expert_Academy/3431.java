import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCaseCount = Integer.parseInt(br.readLine());
		for (int t = 1; t < testCaseCount + 1; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int result = 0;
			int L = Integer.parseInt(st.nextToken());
			int U = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			if (X > U) {
				result = -1;

			} else if (X < L) {
				result = L - X;
			}
			System.out.println("#" + t + " " + result);
		}
	}
}
