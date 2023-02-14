import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정확히 두 봉지 사야하므로 조합이 떠올랐다
 * 1000C2는 약 500,000이고 테스트 케이스가 약 300 이다
 * 500,000 * 300 = 1억 5천만이므로 2초 안에 문제를 풀 수 있다
 */

public class Solution {

	// 과자를 들고 갈 방법이 없는 경우(-1)로 초기화
	static int max = -1;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] snacks = new int[N];

			// 과자 봉지 무게 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			// 과자 조합하여 고르기
			pickSnacks(N, M, snacks, 0, 0, 0);
			sb.append("#").append(t).append(" ").append(max).append("\n");
			max = -1;
		}

		System.out.println(sb.toString());
	}

	static void pickSnacks(int N, int M, int[] snacks, int count, int start, int weight) {
		if (count >= 2) {
			// 한빈이가 들고 갈 수 있으면 최대 무게 갱신
			if (weight <= M) {
				max = Math.max(max, weight);
			}
			return;
		}
		for (int i = start; i < N; i++) {
			pickSnacks(N, M, snacks, count + 1, i + 1, weight + snacks[i]);
		}
	}

}