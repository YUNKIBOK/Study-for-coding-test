import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 아홉 난쟁이 중 일곱 난쟁이를 골라야 한다 -> 조합
 * 조합 후 모자 합을 구해 100이 되는지 확인한다
 * 조합 경우의 수는 9C7이고 모자 합을 구하는 과정에서 7만큼의 시간 복잡도가 발생한다
 * 결국 최악의 경우에 시간 복잡도는 O(1)이다
 */

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] hats = new int[9];
		for (int i = 0; i < 9; i++) {
			hats[i] = Integer.parseInt(in.readLine());
		}

		findSevenMen(0, 0, 0, new boolean[9], hats);
		System.out.println(sb.toString());
	}

	static void findSevenMen(int count, int start, int sum, boolean[] isSelected, int[] hats) {
		if (count >= 7) {
			if (sum == 100) {
				for (int i = 0; i < 9; i++) {
					if (isSelected[i]) {
						sb.append(hats[i]).append("\n");
					}
				}
			}
			return;
		}

		for (int i = start; i < 9; i++) {
			isSelected[i] = true;
			findSevenMen(count + 1, i + 1, sum + hats[i], isSelected, hats);
			isSelected[i] = false;
		}
	}

}