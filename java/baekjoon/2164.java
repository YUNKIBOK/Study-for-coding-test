import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * Queue를 활용하여 문제와 같이 시뮬레이션한다
 * 남는 카드가 한장이면 답을 출력하고 프로그램을 종료한다
 * 시간은 2초(약 2억번의 연산)인데 N은 최대 50만이므로 O(N^2)은 불가능하고 O(NlogN)까지 가능하다
 * 입력이 간단하므로 형변환을 지원하는 Scanner를 사용한다
 * 출력이 간단하므로 표준 출력을 사용한다
 * N이 최대 50만이므로 int 타입을 사용한다
 * 한 번 시뮬레이션을 할 때 큐에서 두 번 빼야 하는데 두 번 뺄 수 없는 경우를 주의한다
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();

		// 큐 생성
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++)
			queue.add(i);

		// 시뮬레이션
		while (queue.size() > 1) {
			queue.poll();
			// 시뮬레이션을 온전히 수행하지 못했는데 카드가 하나 남은 경우
			if (queue.size() == 1)
				break;
			int front = queue.poll();
			queue.add(front);
		}

		System.out.println(queue.peek());
	}

}