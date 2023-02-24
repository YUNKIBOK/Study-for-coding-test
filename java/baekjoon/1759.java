import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * C개의 문자에서 L개를 뽑는 조합을 수행하여 만들 수 있는 모든 암호를 만든다
 * 최악의 경우 15C7 = 약 6,000이다
 * 그 후 암호에 모음이 1개 이상 있는지, 자음이 2개 이상 있는지 체크한다
 */

public class Main {

	static int L;
	static int C;

	// 입력 문자들
	static char[] characters;

	// 만든 암호들
	static List<String> cryptos;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		// 변수 초기화
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		characters = new char[C];
		cryptos = new ArrayList<>();

		// 입력 문자 저장 및 정렬(정렬 시 알파벳 순으로 된 암호를 만들 수 있다)
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++) {
			characters[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(characters);

		// 문자 조합 후 암호 만들기
		combination(0, 0, new char[L]);

		// 암호 사전순 정렬
//		Collections.sort(cryptos);

		for (String crypto : cryptos) {
			sb.append(crypto).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void combination(int count, int start, char[] selection) {
		if (count >= L) {
			// 모음 및 자음 개수 검사
			int vowelCount = 0;
			for (int i = 0; i < L; i++) {
				if (selection[i] == 'a' || selection[i] == 'e' || selection[i] == 'i' || selection[i] == 'o'
						|| selection[i] == 'u') {
					vowelCount++;
				}
			}
//			System.out.println(vowelCount + " " + (L-vowelCount));
			if (vowelCount >= 1 && L - vowelCount >= 2) {
//				System.out.println(Arrays.toString(selection));
				String crypto = "";
				for (int i = 0; i < L; i++) {
					crypto += selection[i];
				}
				cryptos.add(crypto);
			}
			return;
		}

		for (int i = start; i < C; i++) {
			selection[count] = characters[i];
			combination(count + 1, i + 1, selection);
		}
	}

}