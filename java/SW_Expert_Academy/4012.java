import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 절반의 식재료를 조합하고 시뮬레이션한다
 * 조합에서 최악의 경우 16C8인데 이는 2만보다 작다
 * 2만의 경우에 대해서 두 음식의 맛을 구하는 과정은 최악의 경우 16^2이다.
 * 테스트 케이스가 50개인 것을 감안해도 시간 안에 해결할 수 있다
 */

public class Solution {

	static int[][] synergies;
	static int N;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			synergies = new int[N][N];
			// 식재료 시너지 값 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					synergies[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 식재료 조합 후 요리 시뮬레이션
			cook(0, 0, new boolean[N]);
			sb.append(String.format("#%d %d\n", t, min));
			min = Integer.MAX_VALUE;
		}

		System.out.println(sb.toString());
	}

	static void cook(int count, int start, boolean[] isForFood1) {
		if (count >= N / 2) {
			Set<Integer> gredientSet1 = new HashSet<>();
			Set<Integer> gredientSet2 = new HashSet<>();

			for (int i = 0; i < N; i++) {
				if (isForFood1[i])
					gredientSet1.add(i);
				else
					gredientSet2.add(i);
			}

			int tasteDiff = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					if (gredientSet1.contains(i) && gredientSet1.contains(j)) {
						tasteDiff += synergies[i][j];
					} else if (gredientSet2.contains(i) && gredientSet2.contains(j)) {
						tasteDiff -= synergies[i][j];
					}
				}
			}
			// 요리 맛의 차이 최솟값 갱신
			min = Math.min(min, Math.abs(tasteDiff));
			return;
		}

		for (int i = start; i < N; i++) {
			isForFood1[i] = true;
			cook(count + 1, i + 1, isForFood1);
			isForFood1[i] = false;
		}
	}

}