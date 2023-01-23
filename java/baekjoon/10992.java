import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// up part
		for (int i = 0; i < N - 1; i++) {
			System.out.print(" ");
		}
		if (N - 1 > 0)
			System.out.println("*");

		// middle part
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 1; j <= N - 1 - i; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int j = 0; j < 2 * i - 1; j++) {
				System.out.print(" ");
			}
			System.out.println("*");
		}

		// down part
		for (int j = 0; j < 2 * N - 1; j++) {
			System.out.print("*");
		}
	}
}
