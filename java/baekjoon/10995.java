import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			if (i % 2 == 0) {
				System.out.print(" ");
			}

			for (int j = 0; j < N - 1; j++) {
				System.out.print("* ");
			}
			System.out.println("*");
		}
	}
}
