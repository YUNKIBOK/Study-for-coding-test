import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Invalid 조건을 먼저 체크한다
 * Set 자료형을 활용하여 나머지 세 조건을 체크한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int one, two, three, max, sum;
		while (true) {
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			max = Integer.MIN_VALUE;
			one = Integer.parseInt(st.nextToken());
			max = Math.max(max, one);
			two = Integer.parseInt(st.nextToken());
			max = Math.max(max, two);
			three = Integer.parseInt(st.nextToken());
			max = Math.max(max, three);
			sum = one + two + three;

			if (sum == 0) {
				break;
			}

			if (max >= sum - max) {
				System.out.println("Invalid");
				continue;
			}

			if (one == two && two == three && one == three) {
				System.out.println("Equilateral");
			} else if (one != two && two != three && one != three) {
				System.out.println("Scalene");
			} else {
				System.out.println("Isosceles");
			}
		}

	}

}