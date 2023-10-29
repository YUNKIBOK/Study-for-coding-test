import java.io.*;
import java.util.*;

/*
 * 중복 순열인데 다음에 뽑는 건 이전에 뽑은 것보다 값이 크거나 같아야 한다.
 * 최악의 경우에(그냥 중복 순열인 경우) 8^8 < 1억
 */
public class Main {

	public static StringBuilder sb;
	public static int[] results;
	public static int N, M;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		results = new int[M];

		// 오름차순 중복 순열 구하기
		ascendingDuplicatedPermutations(0);
		System.out.println(sb.toString());

	}

	public static void ascendingDuplicatedPermutations(int count) {
		if (count >= M) {
			for (int i = 0; i < M; i++) {
				sb.append(results[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (count == 0) {
				results[count] = i;
				ascendingDuplicatedPermutations(count + 1);
			} else {
				if (results[count - 1] <= i) {
					results[count] = i;
					ascendingDuplicatedPermutations(count + 1);
				}
			}
		}
	}
}