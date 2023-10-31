import java.util.*;
import java.io.*;

/*
 * 순열 구하기
 */
public class Main {

	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	public static int N, M;
	public static int[] numbers;
	public static int[] results;
	public static boolean[] isVisited;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		isVisited = new boolean[N];
		results = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);

		// 순열
		permutations(0);
		System.out.println(sb.toString());

	}

	public static void permutations(int count) {

		if (count >= M) {
			for (int i = 0; i < M; i++) {
				sb.append(results[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isVisited[i] == false) {
				isVisited[i] = true;
				results[count] = numbers[i];
				permutations(count + 1);
				isVisited[i] = false;
			}
		}

	}
}