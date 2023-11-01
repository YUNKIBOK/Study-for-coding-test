import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] numbers;
	static int[] results;
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		results = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		// 중복 순열
		permutations(0);
		System.out.println(sb.toString());
	}

	static void permutations(int count) {
		if (count >= M) {
			for (int i = 0; i < M; i++) {
				sb.append(results[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			results[count] = numbers[i];
			permutations(count + 1);
		}
	}
}