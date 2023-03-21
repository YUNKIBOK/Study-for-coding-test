import java.io.FileInputStream;
import java.util.Scanner;

/*
 * 벌집 주소 사이에 규칙을 찾는다
 * 1 -> 7 -> 19 -> 37 ... 가 각 테두리에서 최대 숫자이다
 * 주소 간 차이는 6 -> 12 -> 18 ... 6의 배수로 증가한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int current = 1; // 현재 도달 가능한 최대 주소
		int step = 1; // 현재까지 지나온 방 수
		int interval = 6; // 주소 간 차이

		// 계산
		while (current < N) {
			current += interval;
			interval += 6;
			step++;
		}

		System.out.println(step);
	}

}