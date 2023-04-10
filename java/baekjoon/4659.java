import java.io.*;
import java.util.*;

/*
 * 문자열을 하나씩 검사한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String pwd = in.readLine();
			if ("end".equals(pwd)) {
				break;
			}

			char[] letters = pwd.toCharArray();

			Set<Character> vowel = new HashSet<>();
			vowel.add('a');
			vowel.add('e');
			vowel.add('i');
			vowel.add('o');
			vowel.add('u');

			boolean isOkay = true;
			boolean isVowelExist = false;
			int vowelSequence = 0;
			int nonVowelSequence = 0;

			for (int i = 0; i < letters.length; i++) {
				char letter = letters[i];
				if (vowel.contains(letter)) {
					isVowelExist = true;
					vowelSequence++;
					nonVowelSequence = 0;
				} else {
					nonVowelSequence++;
					vowelSequence = 0;
				}

				if (vowelSequence >= 3 || nonVowelSequence >= 3) {
					isOkay = false;
					break;
				}

				if (i > 0) {
					char pre = letters[i - 1];
					if (pre != 'e' && pre != 'o' && pre == letter) {
						isOkay = false;
						break;
					}
				}
			}

			if (!isVowelExist) {
				isOkay = false;
			}

			if (isOkay) {
				sb.append("<").append(pwd).append(">").append(" is acceptable.\n");
			} else {
				sb.append("<").append(pwd).append(">").append(" is not acceptable.\n");
			}
		}

		System.out.println(sb.toString());
	}

}
