import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int K, N;
	static int[] lens;
	static long minLen, maxLen;
	static long currentLen, answer;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		minLen = 1;
		maxLen = Integer.MIN_VALUE;
		lens = new int[K];
		for (int k = 0; k < K; k++) {
			lens[k] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, lens[k]);
		}

		currentLen = 1;
		answer = currentLen;

		while (minLen <= maxLen) {
//			System.out.println("현재: " + currentLen);
//			System.out.println(minLen + ", " + maxLen);
			long n = 0L;
			for (int k = 0; k < K; k++) {
				n += (lens[k] / currentLen);
			}
			if (n < N) {
				long temp = currentLen - 1;
				currentLen = (minLen + maxLen) / 2;
				maxLen = temp;
			} else {
				answer = currentLen;
				long temp = currentLen + 1;
				currentLen = (minLen + maxLen) / 2;
				minLen = temp;
			}
		}

		System.out.println(answer);
	}
}