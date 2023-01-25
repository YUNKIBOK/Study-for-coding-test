import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		for (int n = 0; n < N; n++) {
			int score = Integer.parseInt(st.nextToken());
			scores[n] = score;
			max = Math.max(max, score);
		}
		double sum = 0.0;
		for (int score : scores) {
			sum += score * 1.0 / max * 100;
		}
		System.out.println(sum / N);
	}
}
