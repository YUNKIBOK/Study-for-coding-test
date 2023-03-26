
import java.util.*;
import java.io.*;

/*
 * 단어의 문자를 순회하며 카운트한다
 * 카운트하면서 가장 많이 사용된 알파벳 개수를 갱신한다
 * 문자 순회가 끝난 후 가장 많이 사용된 알파벳을 찾는다
 * 해당 알파벳이 여러 개면 '?'를 출력한다
 * 알파벳을 카운트할 때 대소문자를 구별하지 않고 결과는 대문자로 출력해야 하므로
 * 주어진 단어의 문자를 모두 대문자로 바꾸고 시작한다
 * 시간 복잡도는 최악의 경우 100만이므로 2초라는 시간은 굉장히 넉넉한 시간이다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 변수 초기화
		char[] letters = in.readLine().toUpperCase().toCharArray(); // 단어를 대문자로 바꾸고 문자 배열 생성
		int[] counts = new int[26]; // 'A' ~ 'Z' 카운터
		int max = Integer.MIN_VALUE;

		// 문자 순회하며 카운트하고 가장 많이 사용된 알파벳 개수 갱신
		for (int i = 0; i < letters.length; i++) {
			max = Math.max(max, ++counts[letters[i] - 'A']);
		}

		// 가장 많이 사용된 알파벳 찾기
		int count = 0;
		for (int i = 0; i < 26; i++) {
			if (counts[i] == max) {
				sb.setLength(0);
				sb.append((char) (i + 'A'));
				count++;
			}
		}

		if (count == 1) {
			System.out.println(sb.toString());
		} else { // 가장 많이 사용된 알파벳이 여러 개 존재하는 경우
			System.out.println("?");
		}
	}

}
