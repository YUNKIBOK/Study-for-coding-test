import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		short N = Short.parseShort(br.readLine());
		for (short i = 1; i <= N; i++) {
			// left space
			for (short j = (short) (N - i); j > 0; j--) {
				System.out.print(" ");
			}
			// star
			for (short j = 0; j < (short) (2 * i - 1); j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
