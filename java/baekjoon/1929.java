
/*
 * 각 수를 2부터 제곱근까지로 나누어 본다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = N; i <= M; i++) {
			if (i >= 2 && isPrime(i))
				System.out.println(i);
		}
	}

	public static boolean isPrime(int number) {
		int sqrt = (int) Math.sqrt(number * 1.0);

		for (int i = 2; i <= sqrt; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
