import java.util.Scanner;

/*
 * 최대 100만인 N을 1로 만드는 완전 탐색은 시간이 부족하다.
 * 거꾸로 생각하면 시간 복잡도 O(N) 내에 문제를 해결할 수 있다.
 * 점화식은 다음과 같다
 * dp[num] = min(dp[num-1], dp[num/2], dp[num/3]) + 1
 */

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		// 변수 초기화
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		if (N >= 2) {
			dp[2] = 1;
		}
		if (N >= 3) {
			dp[3] = 1;
		}

		// N 만들기
		for (int i = 4; i <= N; i++) {
			int min = dp[i - 1];
			if (i % 2 == 0) {
				min = Math.min(min, dp[i / 2]);
			}
			if (i % 3 == 0) {
				min = Math.min(min, dp[i / 3]);
			}
			dp[i] = min + 1;
		}

		System.out.println(dp[N]);
	}

}