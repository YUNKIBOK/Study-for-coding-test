import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

/*
 * int 배열을 만들고 sort 메서드를 활용한다
 */

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = in.readLine();
		int[] numbers = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			numbers[i] = Integer.parseInt(str.charAt(i) + "");
		}
		Arrays.sort(numbers);
		for (int i = str.length() - 1; i >= 0; i--) {
			out.append(numbers[i] + "");
		}
		out.flush();
	}

}
