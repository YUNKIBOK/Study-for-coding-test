import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte N = Byte.parseByte(br.readLine());
		for (byte j = N; j > 0; j--) {
			for (byte i = j; i > 0; i--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
