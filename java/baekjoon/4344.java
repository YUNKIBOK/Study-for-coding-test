import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[] scores = new int[N];
			int sum = 0;
			for (int n = 0; n < N; n++) {
				int score = Integer.parseInt(st.nextToken());
				sum += score;
				scores[n] = score;
			}
			double avg = sum * 1.0 / N;
			int count = 0;
			for (int score : scores) {
				if (score > avg)
					count++;
			}
			double percentage = Math.round(count * 1.0 / N * 100 * 1000);
			percentage /= 1000;
			System.out.printf("%.3f%%\n", percentage);
		}
	}
}
