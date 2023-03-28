import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 규칙: 인접한 집과 색이 달라야 한다.
 * 1번 집을 어떤 색으로 칠하느냐에 따라 나머지 집을 칠하는 색이 달라진다.
 * 1번 집을 3가지 색으로 칠했을 때의 전체 비용 중 가장 작은 값을 선택한다.
 * -----------------------------------------------------------------------
 * 현재 최소 비용을 선택하더라도 다음 집에 영향을 미치므로 그리디로 접근하면 안된다.
 * dp테이블을 3열로 생성한다
 * dp[p][q]는 p번째 집을 q번째 페인트로 칠했을 때 최소 누적 비용
 * 점화식: dp[p][q] = min(dp[p-1][k], dp[p-1][r]) + costs[p][q] / k와 r은 q와 다른 임의의 페인트
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 집을 빨, 초, 파로 칠하는 비용 저장
		int N = Integer.parseInt(in.readLine());
		int[][] costs = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 점화식에 따른 처리 준비
		int[][] dp = new int[N + 1][3];
		dp[1][0] = costs[1][0];
		dp[1][1] = costs[1][1];
		dp[1][2] = costs[1][2];

		// 점화식에 따른 처리
		for (int i = 2; i <= N; i++) { // 집 순회
			for (int j = 0; j < 3; j++) { // 페인트 순회
				int prevMin = Integer.MAX_VALUE; // 현재 칠하려는 색과 이전 집 색이 다를 때의 누적 최소 비용 구하기
				for (int k = 0; k < 3; k++) {
					if (j != k && dp[i - 1][k] < prevMin) {
						prevMin = dp[i - 1][k];
					}
				}
				dp[i][j] = prevMin + costs[i][j];
			}
		}

		// 최종 최소 비용 구하기
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, dp[N][i]);
		}
		System.out.println(min);
	}

}