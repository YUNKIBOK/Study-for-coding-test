import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[] numbers;
	static int[] results;
	static Map<String, Boolean> map;
	static int N, M;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
//		System.out.println(Arrays.toString(numbers));
		results = new int[M];
		map = new HashMap<>();

		// 조합
		combinations(0, 0);
		System.out.println(sb.toString());
	}

	public static void combinations(int start, int count) {
		if (count >= M) {
			String temp = "";
			for (int i = 0; i < M; i++) {
				temp += (results[i] + " ");
			}

			if (map.get(temp) == null) {
				map.put(temp, true);
				sb.append(temp).append("\n");
			}

			return;
		}

		for (int i = start; i < N; i++) {
			results[count] = numbers[i];
			combinations(i + 1, count + 1);
		}
	}
}