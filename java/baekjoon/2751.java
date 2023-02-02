import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 수의 절대 갑이 1,000,000보다 작거나 같은 정수이므로 int를 사용한다
 * 제한 시간은 2초, N은 1,000,000 이하이므로 O(NlogN)을 사용한다
 */

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(in.readLine());
		int[] numbers = new int[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(in.readLine());
		}

		Arrays.sort(numbers); // O(NlogN)

		for (int i = 0; i < N; i++) {
			out.append(numbers[i] + "\n");
		}
		out.flush();
	}

}
