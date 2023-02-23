import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 수빈이의 위치에서부터 BFS를 수행한다
 * 결국 최대 100,000개의 위치를 모두 탐색하면 BFS를 종료하므로 시간 복잡도는 O(1)이다
 * 탐색 도중 동생을 찾으면 출력하고 프로그램을 종료한다
 * BFS의 너비를 시간으로 바꾸어 생각하면 이해하기 쉽다
 */

public class Main {

	static int N;
	static int K;

	// 수빈이가 각 위치에 도달하는 시간을 저장하는 배열
	static int[] counts = new int[100_000 + 1];

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 각 위치에 도달하는 시간 초기화(도달하지 못한 위치는 존재하지 않는 시간: -1초 활용)
		Arrays.fill(counts, -1);

		BFS(N);
	}

	static void BFS(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		counts[start] = 0;

		while (queue.size() > 0) {
			int current = queue.poll();

			// 수빈이가 동생을 찾은 경우
			if (current == K) {
				System.out.println(counts[current]);
				System.exit(0);
			}

			// 앞으로 한 칸 걷기
			if (current + 1 <= 100_000 && counts[current + 1] == -1) {
				counts[current + 1] = counts[current] + 1;
				queue.add(current + 1);
			}

			// 뒤로 한 칸 걷기
			if (0 <= current - 1 && counts[current - 1] == -1) {
				counts[current - 1] = counts[current] + 1;
				queue.add(current - 1);
			}

			// 순간이동 하기
			if (current * 2 <= 100_000 && counts[current * 2] == -1) {
				counts[current * 2] = counts[current] + 1;
				queue.add(current * 2);
			}
		}
	}

}