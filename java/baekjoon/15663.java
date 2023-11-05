import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, M;
	static int[] numbers;
	static int[] selection;
	static boolean[] isVisited;
	static Map<String, Boolean> map;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
//		System.out.println(Arrays.toString(numbers));

		selection = new int[M];
		isVisited = new boolean[N];
		map = new HashMap<>();

		// 순열
		permutations(0);
		System.out.println(sb.toString());
	}

	public static void permutations(int count) {
		if (count >= M) {
			String numStr = "";
			for (int i = 0; i < M; i++) {
				// 숫자만 넣으면 다른 수열인데 같은 수열로 인식 가능(예. {1, 15}와 {15, 1})
				numStr += (selection[i] + " ");
			}

			if (map.get(numStr) == null) {
				map.put(numStr, true);
				for (int i = 0; i < M; i++) {
					sb.append(selection[i]).append(" ");
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isVisited[i] == false) {
				isVisited[i] = true;
				selection[count] = numbers[i];
				permutations(count + 1);
				isVisited[i] = false;
			}
		}
	}
}