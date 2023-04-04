import java.util.*;
import java.io.*;

/*
 * 2개 남았을 때 3개를 가져오면 이길 수 있다고 생각했는 데 아니었다
 * 2개 남은 경우는 1개만 가져와야 한다
 * 3개는 1개 + 1개 + 1개이므로 결국 턴은 다시 돌아온다
 * 따라서 N이 짝수인지 홀수인지만 알면 된다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		if (N % 2 == 1) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}

}