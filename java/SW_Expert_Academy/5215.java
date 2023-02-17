import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 최대 20개의 재료에 대해 부분조합을 적용하고 햄버거를 만드는 시뮬레이션을 한다
 * 부분조합은 2^20이고 시뮬레이션은 재료를 순회해야 하므로 20이다
 * 테스트 케이스 20개를 수행하는 것에 대해 시간이 넉넉하다
 */

public class Solution {

	static int N;
	static int restrictedKcal;
	static int maxTaste = Integer.MIN_VALUE;
	static Gredient[] gredients;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			restrictedKcal = Integer.parseInt(st.nextToken());

			gredients = new Gredient[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int taste = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				gredients[i] = new Gredient(taste, kcal);
			}

			// 부분조합을 통한 햄버거 만들기
			cookHamburger(0, new boolean[N]);
			sb.append("#").append(t).append(" ").append(maxTaste).append("\n");
			maxTaste = Integer.MIN_VALUE;
		}

		System.out.println(sb.toString());
	}

	static void cookHamburger(int count, boolean[] isSelected) {
		if (count >= N) {
			int totalKcal = 0;
			int totalTaste = 0;

			for (int i = 0; i < N; i++) {
				if (isSelected[i] == true) {
					totalKcal += gredients[i].kcal;
					totalTaste += gredients[i].taste;
				}
				if (totalKcal > restrictedKcal) {
					return;
				}
			}

			if (totalKcal <= restrictedKcal) {
				maxTaste = Math.max(maxTaste, totalTaste);
			}

			return;
		}

		isSelected[count] = true;
		cookHamburger(count + 1, isSelected);

		isSelected[count] = false;
		cookHamburger(count + 1, isSelected);
	}

}

class Gredient {

	int taste;
	int kcal;

	public Gredient(int taste, int kcal) {
		this.taste = taste;
		this.kcal = kcal;

	}
}