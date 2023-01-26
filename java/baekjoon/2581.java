import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 소수 판별은 2부터 제곱근까지의 수로 나누어 본다.
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int minPrime = Integer.MAX_VALUE;
		
		for (int i = M; i <= N; i++) {
			if (isPrime(i) == true) {
				sum += i;
				if (minPrime == Integer.MAX_VALUE) {
					minPrime = i;
				}
			}
		}
		
		if(sum != 0) {
			System.out.println(sum);
			System.out.println(minPrime);
		} else {
			System.out.println(-1);
		}
	}

	public static boolean isPrime(int num) {
		boolean isPrime = true;
		
		if (num < 2) {
			isPrime = false;
		} else {
			int sqrt = (int) Math.sqrt(num * 1.0);
			for (int i = 2; i <= sqrt; i++) {
				if (num % i == 0) {
					isPrime = false;
					break;
				}
			}
		}
		
		return isPrime;
	}
}
