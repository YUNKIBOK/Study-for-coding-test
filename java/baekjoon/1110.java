import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		int count = 0;
		int newNumber = number;
		do {
			int first = newNumber / 10;
			int second = newNumber % 10;
			newNumber = second * 10 + (first + second) % 10;
			count++;
		} while (newNumber != number);
		System.out.println(count);
	}
}
