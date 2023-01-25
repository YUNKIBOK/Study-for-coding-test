import java.io.IOException;

public class Main {
	public static boolean[] isSelfNumber = new boolean[10001];

	public static void main(String args[]) throws NumberFormatException, IOException {
		for (int i = 1; i <= 10000; i++) {
			int newNum = i;
			char[] numbers = (i + "").toCharArray();
			for (char number : numbers) {
				newNum += (number - '0');
			}
			if (newNum <= 10000 && isSelfNumber[newNum] == false) {
				isSelfNumber[newNum] = true;
			}
		}
		for (int i = 1; i <= 10000; i++) {
			if (isSelfNumber[i] == false)
				System.out.println(i);
		}
	}
}
