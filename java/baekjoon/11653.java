
/*
 * 2부터 N이 1이 됳 때까지 계속 나누어 본다.
 * 작은 수로 먼저 나누기 때문에 소인수분해가 된다. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int divisor = 2;

		if (N != 1) {
			while (N > 1) {
				if (N % divisor == 0) {
					N /= divisor;
					System.out.println(divisor);
				} else {
					divisor++;
				}
			}
		}
	}
}
