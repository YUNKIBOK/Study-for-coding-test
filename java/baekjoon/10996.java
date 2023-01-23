import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int oddLine = (N % 2 == 1) ? (N / 2 + 1) : (N / 2);
		int evenLine = N / 2;

		for (int i = 1; i <= 2 * N; i++) {
			if (i % 2 == 1) {
				for (int j = 0; j < oddLine - 1; j++) {
					System.out.print("* ");
				}
				System.out.println("*");
			} else {
				for (int j = 0; j < evenLine; j++) {
					System.out.print(" *");
				}
				if (evenLine > 0)
					System.out.println();
			}
		}
	}
}
