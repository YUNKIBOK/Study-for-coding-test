import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte N = Byte.parseByte(br.readLine());
		for (byte i = 1; i <= N; i++) {
			for (byte j = 0; j < N - i; j++) {
				System.out.print(" ");
			}
			for (byte j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
