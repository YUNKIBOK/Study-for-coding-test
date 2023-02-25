import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 부분합이 누적되므로 부분합을 가장 작게 만들어야 한다
 * 우선순위 큐를 사용하여 임시 파일과 각 장이 쓰여진 파일을 오름차순으로 정렬하고
 * 가장 작은 두 파일을 합치는 작업을 반복한다
 * int 타입 범위를 넘지 않는다
 * 시간 복잡도는 우선순위 큐에서 O(NlogN)이다
 * ---------------------------------------------------
 * 위의 방법은 연속이 되도록 파일을 합칠 수 없다
 * 아이디어가 생각나지 않아 답안을 참고하였다
 * DP 테이블을 생성하여 연속적인 파일 2개를 연결하는 것부터 최소 값을 구해나간다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");

			int[] files = new int[K]; // 챕터별 파일 크기
			int[] sum = new int[K + 1]; // 챕터 파일 누적 합 -> A ~ B까지의 부분 합은 B까지의 누적합 - A 이전까지의 누적합 임을 이용한다
			int[][] dp = new int[K][K]; // {인덱스 0}부터 {인덱스 1}번째 파일까지 결합하는 데 소모되는 최소 비용

			int tempSum = 0; // 임시 누적 합
			for (int j = 0; j < K; j++) {
				files[j] = Integer.parseInt(st.nextToken());
				tempSum += files[j];
				sum[j + 1] = tempSum;
				Arrays.fill(dp[j], Integer.MAX_VALUE); // dp 테이블 초기화
			}

//			System.out.println(Arrays.toString(sum));

			for (int i = 0; i < K - 1; i++) {
				dp[i][i + 1] = files[i] + files[i + 1]; // 연속된 2개 파일을 결합하는 경우 초기화
				dp[i][i] = 0; // 연속된 1개 파일을 결합하는 경우는 없으므로 비용을 0으로 초기화
			}
			dp[K - 1][K - 1] = 0;

			for (int len = 3; len <= K; len++) {// 결합 구간 길이
				for (int start = 0; start + len - 1 <= K - 1; start++) { // 시작점
					int end = start + len - 1;
					for (int mid = start; mid + 1 <= end; mid++) { // 중간점
						dp[start][end] = Math.min(dp[start][end],
								dp[start][mid] + dp[mid + 1][end] + (sum[end + 1] - sum[start]));
					}
				}
			}

//			for (int i = 0; i < K; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}

			sb.append(dp[0][K - 1]).append("\n");
		}

		System.out.println(sb.toString());
	}

}