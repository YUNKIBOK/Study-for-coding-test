import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 재료를 선택하는 순서는 의미가 없다
 * 선택할 수 있는 재료의 개수는 자유다
 * 다만, 1개 이상의 재료를 선택해야 한다
 * -> 부분 집합을 활용한다(단, 공집합은 제외한다)
 * 
 * N은 최대 10이므로 시간 복잡도는 대략 2^10, 시간은 넉넉하다
 * 신맛과 쓴맛 모두 최대일 경우 10억보다 작으므로 int 타입을 사용한다
 * 출력은 한 줄이므로 표준 출력을 그대로 사용하고 입력은 BufferedReader를 사용한다
 */

public class Main {

	static int N;
	static int minGap = Integer.MAX_VALUE;
	static boolean[] isSelected;
	static Ingredient[] ingrdients;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		ingrdients = new Ingredient[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			ingrdients[i] = new Ingredient(sour, bitter);
		}

		cook(0, 0, 1);
		System.out.println(minGap);
	}

	public static void cook(int count, int sum, int mul) {
		if (count >= N) {
			if (sum != 0) // 선택한 재료가 있는 경우에 한해서
				minGap = Math.min(minGap, Math.abs(sum - mul));
			return;
		}

		isSelected[count] = true;
		cook(count + 1, sum + ingrdients[count].bitter, mul * ingrdients[count].sour);

		isSelected[count] = false;
		cook(count + 1, sum, mul);
	}

}

class Ingredient {
	int sour;
	int bitter;

	public Ingredient(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
	}
}