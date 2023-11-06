import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, M;
	static int[] numbers;
	static int[] results;
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

		results = new int[M];
		map = new HashMap<>();
		// 중복 순열
		duplicatedPermutations(0);
		System.out.println(sb.toString());
	}

	public static void duplicatedPermutations(int count) {
		if (count >= M) {
			String str = "";
			for (int i = 0; i < M; i++) {
				str += (results[i] + " ");
			}

			if (map.get(str) == null) {
				map.put(str, true);
				sb.append(str).append("\n");
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			results[count] = numbers[i];
			duplicatedPermutations(count + 1);
		}
	}
}