import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2차원 배열을 생성하고 활용한다
 * dp[i][w]는 w 무게에서 i번 물건까지 넣었을 때 최대 가치이다
 * i번째 물건을 넣을 수 없는 경우 i-1까지 넣은 값이 그대로 적용된다
 * i번째 물건을 넣을 수 있는 경우 물건을 넣는 경우와 물건을 넣지 않는 경우 중 최대 값을 적용한다
 * 가치의 최대 값은 int 범위에서 표현이 가능하다
 * 메모리는 N * K * 4Byte가 필요한데 이는 40MB로 공간 복잡도는 여유롭다
 * 시간 복잡도는 N * K인데 이는 1천만으로 시간 복잡도도 여유롭다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 물건 저장
		Product[] products = new Product[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int weight = Integer.parseInt(st.nextToken());
			int profit = Integer.parseInt(st.nextToken());
			products[i] = new Product(weight, profit);
		}

		// 동적 계획법 적용
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				if (w < products[i].weight) {
					dp[i][w] = dp[i - 1][w];
				} else {
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - products[i].weight] + products[i].profit);
				}
			}
		}

		System.out.println(dp[N][K]);
	}

}

class Product {
	int weight, profit;

	public Product(int weight, int profit) {
		this.weight = weight;
		this.profit = profit;
	}
}