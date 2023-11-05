import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[] numbers;
	static int[] selection;
	static int n, m;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		selection = new int[m];

		// 중복 조합
		duplicatedCombinations(0, 0);
		System.out.println(sb.toString());

	}

	public static void duplicatedCombinations(int start, int count) {
		if (count >= m) {
			for (int i = 0; i < m; i++) {
				sb.append(selection[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < n; i++) {
			selection[count] = numbers[i];
			duplicatedCombinations(i, count + 1);
		}
	}
}