
/*
 * 각 수를 2부터 제곱근까지의 수로 나누어 본다.
 * 소수의 정의에 따라 1은 소수가 될 수 없다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = N;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int number = Integer.parseInt(st.nextToken());
			if (number == 1) {
				count--;
				continue;
			}

			int sqrt = (int) Math.sqrt(number * 1.0);
			for (int i = 2; i <= sqrt; i++) {
				if (number % i == 0) {
					count--;
					break;
				}
			}
		}
		System.out.println(count);
	}
}
