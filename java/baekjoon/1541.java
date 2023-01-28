
/*
 * - 이후에  등장하는 + 숫자들을 괄호로 묶어서 최대한 큰 값을 뺀다.
 * 한 번 - 가 등장한 이후에는 모든 수를 뺄 수 있게 된다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] letters = br.readLine().toCharArray();
		String numbers = "0123456789";
		String partNumber = "";
		int result = 0;
		boolean isPlus = true;

		for (int i = 0; i < letters.length; i++) {
			if (numbers.contains(letters[i] + "")) {
				partNumber += letters[i];
			} else {
				if (isPlus) {
					result += Integer.parseInt(partNumber);
					partNumber = "";
				} else {
					result -= Integer.parseInt(partNumber);
					partNumber = "";
				}
				if (letters[i] == '-') {
					isPlus = false;
				}
			}
		}
		if (isPlus) {
			result += Integer.parseInt(partNumber);
		} else {
			result -= Integer.parseInt(partNumber);
		}

		System.out.println(result);
	}
}
