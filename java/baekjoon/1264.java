import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 모음의 개수
 * 
 * 문장을 하나씩 받아 모음의 수를 센다.
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] letters;
		int count;

		while (true) {
			letters = br.readLine().toCharArray();

			if (letters[0] == '#') {
				break;
			}

			count = 0;

			for (char letter : letters) {
				if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || 
				    letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U') {
					count++;
				}
			}

			sb.append(count).append("\n");
		}

		System.out.println(sb.toString());
		sb.setLength(0);
	}
}