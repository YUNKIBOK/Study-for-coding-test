
/*
 * 돈을 인출하는데 필요한 시간이 짧은 사람부터 인출하면 필요한 시간의 합의 최솟값을 구할 수 있다.
 * 누적된 시간이 뒷 사람에게 영향을 주기 때문이다. 
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] times = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			times[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(times);

		int totalTime = 0;
		for (int i = 0; i < N; i++) {
			int partTime = 0;
			for (int j = 0; j <= i; j++) {
				partTime += times[j];
			}
			totalTime += partTime;
		}

		System.out.println(totalTime);
	}
}
