import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * 우선순위 큐를 사용하는 것까지 생각해냈으나 이후에 방법을 알아내지 못해 답안의 아이디어만 참고함
 * Deque를 활용하는 것과 StringBuilder 두 개를 활용한 것은 본인의 생각해낸 것임
 * ----------------------------------------------------------------------
 * 구사과는 회사 이름이 사전 순으로 가장 앞서게 만들고 싶다
 * 큐브러버는 회사 이름이 사전 순으로 가장 뒤에 오게 만들고 싶다
 * 따라서, 구사과는 문자들을 오름차순 정렬하여 사용하고 큐브러버는 내림차순 정렬하여 사용한다
 * 턴을 번갈아 게임을 진행하기 때문에 구사과는 N/2+1개를 사용하고 큐브러버는 N/2개를 사용하게 된다
 * 구사과의 입장에서 상대방이 가진 가장 큰 패보다 내가 가진 가장 작은 패가 작다면 빠르게 앞자리를 차지해야 한다
 * 왜냐하면 큐브러버가 더 작은 패가 있을지라도 앞자리에 놓지 않을 것이기 때문이다
 * 반면에 내가 가진 가장 작은 패가 상대방이 가진 가장 큰 패보다 크거나 같다면 굳이 앞자리를 차지하지 않아도 된다
 * 상대방의 모든 패가 내 패보다 작기 때문에 그 패들이 앞자리를 차지하면 좋기 때문이다
 * 이런 경우에는 내가 가진 가장 큰 패를 가장 뒤쪽에 배치하여 패를 버려야 한다
 */

public class Main {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder frontSb = new StringBuilder();
		StringBuilder rearSb = new StringBuilder();
		String nineApple = in.readLine();
		String cuteLover = in.readLine();

		int N = nineApple.length();
		PriorityQueue<Character> minHeap = new PriorityQueue<>();
		PriorityQueue<Character> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		Deque<Character> firstPlayer = new LinkedList<>();
		Deque<Character> secondPlayer = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			minHeap.add(nineApple.charAt(i));
			maxHeap.add(cuteLover.charAt(i));
		}
		for (int i = 0; i < (N / 2); i++) {
			firstPlayer.addLast(minHeap.poll());
			secondPlayer.addLast(maxHeap.poll());
		}
		if (N % 2 == 1)
			firstPlayer.addLast(minHeap.poll());

		for (int i = 0; i < N; i++) {
			if (secondPlayer.size() == 0) {
				frontSb.append(firstPlayer.pollFirst());
				break;
			} else if (firstPlayer.size() == 0) {
				frontSb.append(secondPlayer.pollFirst());
				break;
			}
			char koo = firstPlayer.peekFirst();
			char cube = secondPlayer.peekFirst();
			if (i % 2 == 0) { // 구사과 차례
				if (koo < cube) {
					frontSb.append(firstPlayer.pollFirst());
				} else {
					rearSb.insert(0, firstPlayer.pollLast());
				}
			} else { // 큐브러버 차례
				if (cube > koo) {
					frontSb.append(secondPlayer.pollFirst());
				} else {
					rearSb.insert(0, secondPlayer.pollLast());
				}
			}
		}
		System.out.println(frontSb.append(rearSb).toString());
	}
}