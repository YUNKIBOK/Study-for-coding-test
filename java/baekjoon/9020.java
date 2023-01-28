
/*
 * 2부터 제곱근까지 나누어보면 소수인지 판별할 수 있다.
 * 짝수 n에 대하여 n/2부터 2까지 골드바흐 파티션이 되는지 체크한다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			for (int i = n / 2; i >= 2; i--) {
				if (isPrime(i) && isPrime(n - i)) {
					if (i < n - i) {
						System.out.println(i + " " + (n - i));
					} else {

						System.out.println(n - i + " " + i);
					}
					break;
				}
			}
		}
	}

	public static boolean isPrime(int number) {
		if (number == 1) {
			return false;
		}

		int sqrt = (int) Math.sqrt(number * 1.0);
		for (int i = 2; i <= sqrt; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
