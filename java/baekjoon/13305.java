import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 이전 주유소 중 가장 저렴한 곳에서 기름을 넣어야 한다.
 * DP 테이블에 이전까지 가장 저렴했던 주요소 리터당 가격을 저장하고 활용한다.
 * 가격과 거리의 최대 범위에 따라 long 자료형을 사용한다.
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		int N = Integer.parseInt(in.readLine());

		// 거리 저장
		st = new StringTokenizer(in.readLine(), " ");
		int[] distances = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			distances[i] = Integer.parseInt(st.nextToken());
		}

		// 주요소 거리당 가격 저장
		st = new StringTokenizer(in.readLine(), " ");
		int[] prices = new int[N];
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}

		// 가장 저렴했던 주요소 거리당 가격 저장(dp[n]: n번째 주요소까지 중 최저 가격)
		int[] dp = new int[N - 1];
		dp[0] = prices[0];
		for (int i = 1; i < N - 1; i++) {
			dp[i] = Math.min(dp[i - 1], prices[i]);
		}

		// 비용 계산
		long answer = 0;
		for (int i = 0; i < N - 1; i++) {
			answer += (long) dp[i] * distances[i];
		}

		// 출력
		System.out.println(answer);
	}

}
