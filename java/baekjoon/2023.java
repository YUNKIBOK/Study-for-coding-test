import java.util.Scanner;

/*
 * 범위에 해당하는 수를 검사하면 시간이 초과된다 최대 8자리인 경우 약 9억개의 수를 검사해야 함
 * 1의 자리까지 소수가 되어야 신기한 소수이므로 순열 생성에 이용할 수를 적절히 조절한다
 * 짝수는 무조건 2로 나누어 떨어지므로 제거, 2는 소수이므로 남긴다
 */

public class Main {

	static int[] numbers = { 1, 2, 3, 5, 7, 9 };
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		findWeiredPrimeNum(0, 0);
		System.out.println(sb.toString());
	}

	public static void findWeiredPrimeNum(int count, int number) {
		if (count == N) {
			if (number >= Math.pow(10, N - 1)) {
				boolean isPrime = true;
				int temp = number;
				while (temp > 0) {
					if (!isPrime(temp)) {
						isPrime = false;
						break;
					} else {
						temp = (int) (temp / 10);
					}
				}
				if (isPrime) {
					sb.append(number).append("\n");
				}
			}
			return;
		}

		for (int i = 0; i < numbers.length; i++) {
			findWeiredPrimeNum(count + 1, (int) (number + (numbers[i] * Math.pow(10, N - 1 - count))));
		}
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		if (num == 1) {
			return false;
		}
		return true;
	}

}