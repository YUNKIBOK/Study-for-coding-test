import java.util.*;
import java.io.*;

/*
 * 수빈이가 동생보다 앞 서 있는 경우는 뒤로 걸어가는 것을 반복해야 하므로 거리 차이를 반환한다
 * 그렇지 않은 경우에는 순간 이동을 하는 것부터 BFS를 수행한다
 * 순간 이동은 먼 거리를 이동할 수 있지만 시간이 소비되지 않으므로 다른 이동보다 먼저 수행해야 최적의 시간을 구할 수 있다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 수빈이가 동생보다 앞 선 경우
		if (N >= K) {
			System.out.println(N - K);
			System.exit(0);
		}

		bfs(N, K);
	}

	static void bfs(int start, int K) {
		ArrayDeque<Integer> queue = new ArrayDeque();
		boolean[] isVisited = new boolean[100_000 + 1];
		isVisited[start] = true;
		queue.add(start);

		int second = 0;

		while (queue.size() > 0) {
			int size = queue.size();
			while (size-- > 0) {
				int current = queue.poll();
				// 동생을 찾은 경우
				if (current == K) {
					System.out.println(second);
					System.exit(0);
				}
				// 순간 이동 하는 경우
				if (current * 2 <= 100_000 && isVisited[current * 2] == false) {
					queue.addFirst(current * 2);
					isVisited[current * 2] = true;
					size++; // 시간 흐름 없이 바로 탐색할 수 있게 한다
				}
				// 앞으로 걸어가는 경우
				if (current + 1 <= 100_000 && isVisited[current + 1] == false) {
					queue.add(current + 1);
					isVisited[current + 1] = true;
				}
				// 뒤로 걸어가는 경우
				if (current - 1 >= 0 && isVisited[current - 1] == false) {
					queue.add(current - 1);
					isVisited[current - 1] = true;
				}
			}
			second++;
		}
		System.out.println(second);
	}

}
