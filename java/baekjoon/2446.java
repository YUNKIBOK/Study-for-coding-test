import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte N = Byte.parseByte(br.readLine());

		// up part
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * (N - i) + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		// middle part
		for (int i = 0; i < N - 1; i++) {
			System.out.print(" ");
		}
		System.out.println("*");

		// down part
		for (int i = N - 1; i >= 1; i--) {
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * (N - i) + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
