
import java.util.*;
import java.io.*;

/*
 * 두 연산에 대한 DFS를 수행한다
 * 최악의 경우 2^49번의 연산을 수행하므로 가지치기를 해야 한다
 * 연산을 수행한 결과를 T에서 찾을 수 없으면 더 이상 탐색하지 않는다
 */
public class Main {

	static String S, T;
	static String reversedT; // 거꾸로된 문자열 T
	static StringBuilder sb; // S에서 T로 만들어 가는 문자열

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));

		// 변수 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		S = in.readLine();
		T = in.readLine();
		sb = new StringBuilder(T);
		reversedT = sb.reverse().toString();
		sb.setLength(0);
		sb.append(S);

		dfs(0);
		System.out.println(0);
	}

	static void dfs(int depth) {
//		System.out.println(depth + " " + T +" " +sb.toString());

		if (sb.length() > T.length()) { // T보다 긴 문자열은 탐색 중지
			return;
		}

		if (T.equals(sb.toString())) { // T가 되었으면 탐색 중지
			System.out.println(1);
			System.exit(0);
		}

		// 연산 1번 결과가 유효하면 탐색 지속
		sb.append("A");
		if (T.contains(sb.toString()) || reversedT.contains(sb.toString())) {
			dfs(depth + 1);
		}
		sb.setLength(sb.length() - 1);

		// 연산 2번 결과가 유효하면 탐색 지속
		sb.append("B");
		sb.reverse();
		if (T.contains(sb.toString()) || reversedT.contains(sb.toString())) {
			dfs(depth + 1);
		}
		sb.reverse();
		sb.setLength(sb.length() - 1);
	}

}
