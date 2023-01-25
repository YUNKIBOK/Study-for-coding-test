import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] blocks = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int n = 0; n < N; n++) {
				blocks[n] = Integer.parseInt(st.nextToken());
			}
			int upDifficulty = 0;
			int downDifficulty = 0;
			for (int n = 0; n < N - 1; n++) {
				int diff = blocks[n + 1] - blocks[n];
				if (diff > 0) {
					if (diff > upDifficulty)
						upDifficulty = diff;
				} else {
					if (-diff > downDifficulty)
						downDifficulty = -diff;
				}
			}
			System.out.println("#" + t + " " + upDifficulty + " " + downDifficulty);
		}
	}
}
