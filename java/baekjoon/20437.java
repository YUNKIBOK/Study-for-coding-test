
import java.util.*;
import java.io.*;

/*
 * 주어진 문자열을 순회하며 문자의 위치를 저장해둔다.
 * K번 이상 등장한 문자의 문자 위치를 순회하며 3번과 4번 답을 구한다
 * 3번은 결국 첫 번째와 마지막 글자가 같으면서 가장 짧은 연속 문자열 길이를 구하는 것이다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			// 문자열 W 및 K 저장
			String str = in.readLine();
			int K = Integer.parseInt(in.readLine());

			// 'a'부터 'z'까지 문자 등장 위치를 저장하는 리스트들의 배열 선언 및 초기화
			List<Integer>[] list = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				list[i] = new ArrayList<>();
			}

			// 문자열 순회하며 문자 등장 위치 저장
			for (int i = 0; i < str.length(); i++) {
				list[str.charAt(i) - 'a'].add(i);
			}

			// 3번 답 구하기
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 26; i++) {
				if (list[i].size() >= K) { // K번 이상 등장한 문자만 검사
					for (int j = 0; j + K - 1 < list[i].size(); j++) {
						min = Math.min(min, Math.abs(list[i].get(j) - list[i].get(j + K - 1)) + 1);
					}
				}
			}

			// 3번 답이 없는 경우
			if (min == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
				continue;
			}

			// 4번 답 구하기
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 26; i++) {
				if (list[i].size() >= K) { // K번 이상 등장한 문자만 검사
					for (int j = 0; j + K - 1 < list[i].size(); j++) {
						max = Math.max(max, Math.abs(list[i].get(j) - list[i].get(j + K - 1)) + 1);
					}
				}
			}

			// 4번 답이 없는 경우
			if (max == Integer.MIN_VALUE) {
				sb.append(-1).append("\n");
			} else { // 3번, 4번 답이 있는 경우
				sb.append(min).append(" ").append(max).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

}
