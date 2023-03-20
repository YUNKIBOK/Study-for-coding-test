import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * H를 N+1로 나눈 몫과 W를 M+1로 나눈 몫을 곱한다
 * 마지막 사람을 앉힐 수 있는지 확인하는 것을 주의한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int rowCnt = H % (N + 1) > 0 ? H / (N + 1) + 1 : H / (N + 1);
		int colCnt = W % (M + 1) > 0 ? W / (M + 1) + 1 : W / (M + 1);

		int result = rowCnt * colCnt;
		System.out.println(result);
	}

}