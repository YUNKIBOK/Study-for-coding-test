
/*
 * 2부터 제곱근까지 나누어보면 소수인지 판별할 수 있다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			int number = Integer.parseInt(br.readLine());
			if (number == 0) {
				break;
			}
			int count = 0;
			for (int i = number + 1; i <= 2 * number; i++) {
				if (isPrime(i)) {
					count++;
				}
			}
			System.out.println(count);
		} while (true);
	}

	public static boolean isPrime(int number) {
		if (number < 2) {
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
