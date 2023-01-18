import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		short N = Short.parseShort(br.readLine());
		for (short i = 0; i < N; i++) {
			// left space
			for (short j = 0; j < i; j++) {
				System.out.print(" ");
			}
			// star
			for (short j = 0; j < (short) (2 * (N - i) - 1); j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
