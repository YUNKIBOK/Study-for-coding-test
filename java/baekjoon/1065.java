import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int hansuCount = N;
		for (int i = 1; i <= N; i++) {
			char[] numLetters = (i + "").toCharArray();
			int size = numLetters.length;
			if (size >= 2) {
				int diff = numLetters[1] - numLetters[0];
				for (int j = 1; j < size - 1; j++) {
					if (diff != numLetters[j + 1] - numLetters[j]) {
						hansuCount--;
						break;
					}
				}
			}
		}
		System.out.println(hansuCount);
	}
}
