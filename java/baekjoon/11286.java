import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 우선순위 큐를 만들어서 활용한다
 * 정렬 기준은 절댓값 내림차순, 실제 값 오름차순으로 한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 절댓값 내림차순, 실제 값 오름차순으로 정렬하는 우선순위 큐 생성
		PriorityQueue<Integer> absMinHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else if (Math.abs(o1) == Math.abs(o2)) { // 절댓값이 같으면 실제 값 비교
					if (o1 > o2) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			}

		});

		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(in.readLine());
			if (input == 0) {
				if (absMinHeap.isEmpty()) { // 우선순위 큐가 비었으면 '0' 출력
					sb.append("0\n");
				} else { // 우선순위 큐에 원소가 존재하면 꺼내고 출력
					sb.append(String.format("%d\n", absMinHeap.poll()));
				}
			} else { // x가 0이 아니면 우선순위 큐에 추가
				absMinHeap.add(input);
			}
		}

		System.out.println(sb.toString());
	}

}