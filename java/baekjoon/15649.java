import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 순열을 만들고 출력한다
 */

public class Main {

	public static StringBuilder sb = new StringBuilder();
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] isSelected = new boolean[N + 1];

		permutation(isSelected, 0, N, M);
	}

	public static void permutation(boolean[] isSelected, int count, int n, int r) throws IOException {
		// 종료 조건: r개의 수를 뽑았을 때
		if (count >= r) {
			sb.setLength(sb.length() - 1);
			sb.append("\n");
			out.append(sb.toString());
			out.flush();
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (isSelected[i] == false) {
				isSelected[i] = true;
				sb.append(i + " ");
				permutation(isSelected, count + 1, n, r);
				sb.setLength(sb.length() - 2);
				isSelected[i] = false;
			}
		}
	}

}
