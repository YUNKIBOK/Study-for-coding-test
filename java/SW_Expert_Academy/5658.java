import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char[] numbers = in.readLine().toCharArray();
			Set<String> numbersSet = new HashSet<>();
			List<String> numbersStr = new ArrayList<>();
			for (int interval = 0; interval < N / 4; interval++) {
				for (int start = 0; start < N; start += N / 4) {
					for (int len = 0; len < N / 4; len++) {
						sb2.append(numbers[(start + interval + len) % N]);
					}
					String temp = sb2.toString();
					if (!numbersSet.contains(temp)) {
						numbersSet.add(temp);
						numbersStr.add(temp);
					}
					sb2.setLength(0);
				}
			}
			Collections.sort(numbersStr, Collections.reverseOrder());
//			System.out.println(Arrays.toString(numbersStr.toArray()));
			String target = numbersStr.get(K - 1);
//			System.out.println(target);
			Long result = 0L;
			for (int i = target.length() - 1; i >= 0; i--) {
				int temp = target.charAt(i) >= 'A' ? target.charAt(i) - 'A' + 10 : target.charAt(i) - '0';
				result += temp * (long) Math.pow(16, target.length() - 1 - i);
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

}
