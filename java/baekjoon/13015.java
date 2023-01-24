import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// up part
		for (int i = 0; i < N; i++) {
			System.out.print("*");
		}
		for (int i = 0; i < 2 * (N - 1) - 1; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < N; i++) {
			System.out.print("*");
		}
		System.out.println();

		// up middle part
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 0; j < i; j++)
				System.out.print(" ");
			System.out.print("*");
			for (int k = 0; k < N - 2; k++)
				System.out.print(" ");
			System.out.print("*");
			for (int p = 0; p < 2 * (N - 1 - i) - 1; p++)
				System.out.print(" ");
			System.out.print("*");
			for (int k = 0; k < N - 2; k++)
				System.out.print(" ");
			System.out.println("*");
		}

		// middle part
		for (int i = 0; i < N - 1; i++)
			System.out.print(" ");
		System.out.print("*");
		for (int i = 0; i < N - 2; i++)
			System.out.print(" ");
		System.out.print("*");
		for (int i = 0; i < N - 2; i++)
			System.out.print(" ");
		System.out.println("*");

		// down middle part
		for (int i = N - 2; i >= 1; i--) {
			for (int j = 0; j < i; j++)
				System.out.print(" ");
			System.out.print("*");
			for (int k = 0; k < N - 2; k++)
				System.out.print(" ");
			System.out.print("*");
			for (int p = 0; p < 2 * (N - 1 - i) - 1; p++)
				System.out.print(" ");
			System.out.print("*");
			for (int k = 0; k < N - 2; k++)
				System.out.print(" ");
			System.out.println("*");
		}

		// down part
		for (int i = 0; i < N; i++) {
			System.out.print("*");
		}
		for (int i = 0; i < 2 * (N - 1) - 1; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < N; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
}
