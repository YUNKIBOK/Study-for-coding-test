import java.util.*;
import java.io.*;

/*
 * 중복순열로 구한다.
 * 탈출 조건은 정수 n이 되거나 n보다 커지는 경우이다.
 */
public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static int count;
	static int N;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			count = 0;
			duplicatedPermutations(0);
			sb.append(count).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void duplicatedPermutations(int current) {
		if (current >= N) {
			if (current == N) {
				count++;
			}
			return;
		}

		for (int i = 1; i <= 3; i++) {
			duplicatedPermutations(current + i);
		}
	}
}