import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount = Integer.parseInt(br.readLine());
		for (int i = 1; i < testCaseCount + 1; i++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sum = 0;
			double avg;
			int[] numbers = new int[N];
			for (int j = 0; j < N; j++) {
				int number = Integer.parseInt(st.nextToken());
				numbers[j] = number;
				sum += number;
			}
			avg = sum / (N * 1.0);
			int count = 0;
			for (int j = 0; j < N; j++) {
				if ((double) numbers[j] <= avg) {
					count++;
				}
			}
			System.out.println("#" + i + " " + count);
		}
	}
}
